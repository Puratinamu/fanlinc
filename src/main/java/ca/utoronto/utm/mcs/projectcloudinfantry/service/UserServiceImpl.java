package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.*;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomInfoResult;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserToFandomRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RelationshipRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToContact;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.RelationshipRequestMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.UserContactInfoMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.UserMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.*;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.AddContactRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.LoginRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.*;
import ca.utoronto.utm.mcs.projectcloudinfantry.security.BcryptUtils;
import ca.utoronto.utm.mcs.projectcloudinfantry.token.TokenService;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FandomRepository fandomRepository;
    private final UserToFandomRepository userToFandomRepository;
    private final UserToContactRepository userToContactRepository;

    private final UserMapper userMapper;
    private final RelationshipRequestMapper relationshipRequestMapper;
    private final UserContactInfoMapper userContactInfoMapper;
    private final RelationshipService relationshipService;
    private final TokenService tokenService;

    public UserServiceImpl(UserRepository userRepository, FandomRepository fandomRepository, UserToFandomRepository userToFandomRepository, UserToContactRepository userToContactRepository, UserMapper userMapper, RelationshipRequestMapper relationshipRequestMapper, UserContactInfoMapper userContactInfoMapper, RelationshipService relationshipService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.fandomRepository = fandomRepository;
        this.userToFandomRepository = userToFandomRepository;
        this.userToContactRepository = userToContactRepository;
        this.userMapper = userMapper;
        this.relationshipRequestMapper = relationshipRequestMapper;
        this.userContactInfoMapper = userContactInfoMapper;
        this.relationshipService = relationshipService;
        this.tokenService = tokenService;
    }

    @Override
    public User registerUser(RegistrationRequest request) {
        // Validate that email, username, and password are not empty
        if (request.getEmail().isEmpty() || request.getUsername().isEmpty() || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Email, username, or password cannot be empty.");
        }

        // Validate email by checking if existing users have it
        boolean emailExists = userRepository.findByEmail(request.getEmail()) != null;
        if (emailExists) {
            // If user already exists, then return 400 error
            throw new UserAlreadyExistsException("User with email \"" + request.getEmail() + "\" already exists.");
        }

        // Create user
        User newUser = userMapper.toUser(request);
        // Bcrypt password to store in db
        String password = newUser.getPassword();
        password = BcryptUtils.encodePassword(password);
        // Map the list of request objects {"id":1, "level": CASUAL} to list of Fandom Objects
        List<RelationshipRequest> relRequests = new ArrayList<>();
        for (Map<String, Object> m : request.getFandoms()) {
            relRequests.add(relationshipRequestMapper.toRelationshipRequest(m));
        }
        // Find fandoms by id and save to user
        List<Fandom> fandoms = new ArrayList<>();
        for (RelationshipRequest r : relRequests) {
            Optional<Fandom> optionalFandom;
            try {
                optionalFandom = fandomRepository.findById(r.getOidFandom());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Fandom ID '" + r.getOidFandom() + "' is not a long type");
            }
            // If fandom does not exist, throw exception
            if (!optionalFandom.isPresent()) {
                throw new FandomNotFoundException("Fandom with ID '" + r.getOidFandom() + "' not found");
            }
            Fandom fandom = optionalFandom.get();
            fandoms.add(fandom);
        }
        // Set password and fandoms
        newUser.setPassword(password);
        newUser.setFandoms(fandoms);
        // Set date of creation
        Date date = new Date();
        newUser.setCreationTimestamp(date);
        newUser.setLastLoginTimestamp(date);
        newUser.setLastUpdateTimestamp(date);

        newUser = userRepository.save(newUser);

        for (RelationshipRequest r : relRequests) {
            relationshipService.addUserToFandom(newUser.getOidUser(), r.getOidFandom(), r.getLevel());
        }
        return newUser;
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        // Validate that username and password are not empty
        if (request.getEmail().isEmpty() || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException();
        }
        // Check if username exists
        User user = userRepository.findByEmail(request.getEmail());

        // If user does not exist, throw error
        if (user == null) {
            throw new UserNotFoundException();
        } else {
            // If username exists, check that his password matches the one from the request, else throw error
            if(!BcryptUtils.passwordEncoder().matches(request.getPassword(), user.getPassword()))
                throw new NotAuthorizedException();
        }
        LoginResponse response = new LoginResponse();
        response.setJwt( tokenService.generateToken(user.getOidUser(), new HashMap<>()));
        response.setOidUser(user.getOidUser());
        return response;
    }
  
    @Override
    public User getUserByUsername(User user) {
        return userRepository.findByUsername(user.getUsername());
    }

    @Override
    public ProfileResponse getProfile(String oidUser) {
        // Check request body args
        Optional<User> user = userRepository.findById(MapperUtils.toLong(oidUser));
        if (!user.isPresent()) throw new UserNotFoundException();
        User foundUser = user.get();

        // Get the list of fandoms a user belongs to and it's corresponding level of interest
        List<FandomInfoResult> results = fandomRepository.getFandomsAndRelationshipsByOidUser(foundUser.getOidUser());

        List<UserFandomAndRelationshipInfo> infoList = new ArrayList<>();
        for (FandomInfoResult result: results) {
            UserFandomAndRelationshipInfo info = new UserFandomAndRelationshipInfo();
            info.setOidFandom(result.getFandom().getOidFandom());
            info.setName(result.getFandom().getName());
            info.setDescription((result.getFandom().getDescription()));
            info.setRelationship(result.getRelationship());
            infoList.add(info);
        }

        // The constructor handles setting the appropriate fields.
        return new ProfileResponse(foundUser, infoList);
    }

    @Override
    public void addContact(AddContactRequest request) {

        // If user and contactId are the same, throw exception
        if (request.getOidUser().equals(request.getContactOidUser()))
            throw new IllegalArgumentException();

        // Check if user exists
        Optional<User> user = userRepository.findById(request.getOidUser());
        if (!user.isPresent()) throw new UserNotFoundException();
        User foundUser = user.get();

        // Check if contact user exists
        Optional<User> contactUser = userRepository.findById(request.getContactOidUser());
        if (!contactUser.isPresent()) throw new UserNotFoundException();
        User foundContactUser = contactUser.get();

        // Try to get the relationship if it already exists
        UserContactInfoResult dbRelationship = userToContactRepository.findByUserIdAndUserContactId(
                request.getOidUser(), request.getContactOidUser());

        // If not exists, create it
        if (dbRelationship == null) {
            // Create the relationship
            UserToContact relationship = new UserToContact(foundUser, foundContactUser);
            userToContactRepository.save(relationship);
        } else {
            // If it already exists, throw 409 CONFLICT error
            throw new BelongsToRelationshipAlreadyExists();
        }

    }


    @Override
    public UserContactsResponse getContacts(Long oidUser) {
        // Check request body args
        Optional<User> user = userRepository.findById(MapperUtils.toLong(oidUser));
        if (!user.isPresent()) throw new UserNotFoundException();
        User foundUser = user.get();

        // Get the list of fandoms a user belongs to and it's corresponding level of interest
        List<UserContactInfoResult> results = userToContactRepository.getUserContactsByOidUser(foundUser.getOidUser());

        // Map all the results to a list of UserContactInfo
        List<UserContactInfo> infoList = userContactInfoMapper.toAllUserContactInfo(results);

        // The constructor handles setting the appropriate fields.
        return new UserContactsResponse(infoList);
    }
}
