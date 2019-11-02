package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomInfoResult;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserToFandomRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RelationshipRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.RelationshipRequestMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.UserMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.FandomInfo;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.ProfileResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.NotAuthorizedException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserAlreadyExistsException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.LoginRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.security.BcryptUtils;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FandomRepository fandomRepository;
    private final UserToFandomRepository userToFandomRepository;

    private final UserMapper userMapper;
    private final RelationshipRequestMapper relationshipRequestMapper;
    private final RelationshipService relationshipService;

    public UserServiceImpl(UserRepository userRepository, FandomRepository fandomRepository, UserToFandomRepository userToFandomRepository, UserMapper userMapper, RelationshipRequestMapper relationshipRequestMapper, RelationshipService relationshipService) {
        this.userRepository = userRepository;
        this.fandomRepository = fandomRepository;
        this.userToFandomRepository = userToFandomRepository;
        this.userMapper = userMapper;
        this.relationshipRequestMapper = relationshipRequestMapper;
        this.relationshipService = relationshipService;
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
            // Add relationship between fandom and user with level of interest
            relationshipService.addUserToFandom(
                    newUser.getOidUser(), r.getOidFandom(), r.getLevel());
        }
        return newUser;
    }

    @Override
    public void loginUser(LoginRequest request) {
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
    }
  
    @Override
    public User getUserByUsername(User user) {
        return userRepository.findByUsername(user.getUsername());
    }

    @Override
    public ProfileResponse getProfile(Long oidUser) {
        Optional<User> user = userRepository.findById(oidUser);
        if (!user.isPresent()) throw new UserNotFoundException();
        User foundUser = user.get();

        // Get the list of fandoms a user belongs to and it's corresponding level of intrest
        List<FandomInfoResult> results = fandomRepository.getFandomsAndRelationshipsByOidUser(foundUser.getOidUser());

        List<FandomInfo> infoList = new ArrayList<>();
        for (FandomInfoResult result: results) {
            FandomInfo info = new FandomInfo();
            info.setOidFandom(result.getFandom().getOidFandom());
            info.setName(result.getFandom().getName());
            info.setDescription((result.getFandom().getDescription()));
            info.setRelationship(result.getRelationship());
            infoList.add(info);
        }

        // The constructor handles setting the appropriate fields.
        return new ProfileResponse(foundUser, infoList);
    }
}
