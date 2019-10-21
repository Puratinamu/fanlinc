package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserAlreadyExistsException;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserToFandomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RelationshipServiceImpl implements RelationshipService {
    private final UserToFandomRepository userToFandomRepository;
    private final UserRepository userRepository;

    public RelationshipServiceImpl (UserToFandomRepository userToFandomRepository, UserRepository userRepository) {
        this.userToFandomRepository = userToFandomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserToFandom addUserToFandom(String username, Long oidFandom, String type) {
        User user = userRepository.findByUsername(username);

        // Todo: Once fandom add/get is merged, this line will be correct
        // Fandom fandom = fandomRepository.getFandom(oidFandom);

        // Mock Fandom for now
        Fandom fandom = new Fandom();
        fandom.setName("League of Legends");
        fandom.setDescription("The league of legend fan club");

        // Create the relationship
        UserToFandom relationship = new UserToFandom(user, fandom, type);

        return userToFandomRepository.save(relationship);

    }
}
