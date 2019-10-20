package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        User existingUser = userRepository.getUserByUsername(user.getUsername());
        if (existingUser == null) {
            // Create a new User
            user.setOidUser(UUID.randomUUID());
            return userRepository.save(user);
        }
        // Otherwise return the one in the db
        return existingUser;
    }

    @Override
    public User getUserByUsername(User user) {
        return userRepository.getUserByUsername(user.getUsername());
    }

}
