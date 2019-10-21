package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.NotAuthorizedException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserAlreadyExistsException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.LoginRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.security.BcryptUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(RegistrationRequest request) {
        //TODO: validate user

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
        password = BcryptUtils.passwordEncoder().encode(password);

        // Initialize date of creation
        Date date = new Date();

        // Create user
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setUsername(request.getUsername());
        newUser.setPassword(password);
        newUser.setDescription(request.getDescription());
        // TODO: Create list of Fandoms from list of Fandom Ids
        // newUser.setFandoms();
        newUser.setCreationTimestamp(date);
        newUser.setLastLoginTimestamp(date);
        newUser.setLastUpdateTimestamp(date);

        return userRepository.save(newUser);
    }

    @Override
    public User loginUser(LoginRequest request) {
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
            // Return user
            else return user;
        }
    }
}
