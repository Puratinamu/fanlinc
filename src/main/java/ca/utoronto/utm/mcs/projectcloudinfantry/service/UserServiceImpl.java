package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserAlreadyExistsException;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserToFandomRepository;

import ca.utoronto.utm.mcs.projectcloudinfantry.response.ProfileResponse;
import org.springframework.context.annotation.Bean;

import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.NotAuthorizedException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserAlreadyExistsException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.LoginRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.security.BcryptUtils;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FandomRepository fandomRepository;
    private final UserToFandomRepository userToFandomRepository;

    public UserServiceImpl(UserRepository userRepository,
                           FandomRepository fandomRepository,
                           UserToFandomRepository userToFandomRepository) {
        this.userRepository = userRepository;
        this.fandomRepository = fandomRepository;
        this.userToFandomRepository = userToFandomRepository;
    }

    @Override
    public void registerUser(RegistrationRequest request) {
        // Validate that email, username, and password are not empty
        if (request.getEmail().isEmpty() || request.getUsername().isEmpty() || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException();
        }

        // Validate email by checking if existing users have it
        boolean emailExists = userRepository.findByEmail(request.getEmail()) != null;
        if (emailExists) {
            // If user already exists, then return 400 error
            throw new UserAlreadyExistsException();
        }
        // Bcrypt password to store in db

        String password = request.getPassword();
        password = BcryptUtils.encodePassword(password);

        // Initialize date of creation
        Date date = new Date();

        // Create user
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setUsername(request.getUsername());
        newUser.setPassword(password);
        newUser.setDescription(request.getDescription());
        // Create list of Fandoms from list of Fandom Ids
        List<String> fandomIds = request.getFandoms();
        List<Fandom> fandoms = new ArrayList<>();
        for (String f : fandomIds) {
            Optional<Fandom> optionalFandom = fandomRepository.findById(Long.valueOf(f));
            // If fandom does not exist, throw exception
            if (!optionalFandom.isPresent()) {
                throw new FandomNotFoundException();
            }
            Fandom fandom = optionalFandom.get();
            fandoms.add(fandom);
        }
        newUser.setFandoms(fandoms);

        newUser.setCreationTimestamp(date);
        newUser.setLastLoginTimestamp(date);
        newUser.setLastUpdateTimestamp(date);

        userRepository.save(newUser);
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

        // Get and overwrite this local User obj with the proper fandoms
        List<Fandom> fandoms = fandomRepository.getFandomsByUserId(foundUser.getOidUser());
        foundUser.setFandoms(fandoms);

        // We only want their Id, Username, Description, and Fandoms. The profile contructor handles that
        return new ProfileResponse(foundUser);
    }
}
