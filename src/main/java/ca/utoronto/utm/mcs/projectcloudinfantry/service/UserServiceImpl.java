package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserAlreadyExistsException;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        //TODO: validate user

        // Validate email and username by checking if existing users have them
        boolean emailExists = userRepository.findByEmail(user.getEmail()) != null;
        boolean userExists = userRepository.findByUsername(user.getUsername()) != null;
        if (emailExists || userExists) {
            // If user already exists, then return 400 error
            throw new UserAlreadyExistsException();
        }
        // Bcrypt password to store in db
        String password = user.getPassword();
        user.setPassword(passwordEncoder().encode(password));

        return userRepository.save(user);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
