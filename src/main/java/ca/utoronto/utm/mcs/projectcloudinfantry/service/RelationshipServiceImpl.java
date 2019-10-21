package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserAlreadyExistsException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserToFandomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RelationshipServiceImpl implements RelationshipService {
    private final UserToFandomRepository userToFandomRepository;
    private final UserRepository userRepository;
    private final FandomRepository fandomRepository;

    public RelationshipServiceImpl (UserToFandomRepository userToFandomRepository,
                                    UserRepository userRepository,
                                    FandomRepository fandomRepository) {
        this.userToFandomRepository = userToFandomRepository;
        this.userRepository = userRepository;
        this.fandomRepository = fandomRepository;
    }

    @Override
    public UserToFandom addUserToFandom(String username, String fandomName, String type) {
        // Get the fandom and user.
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UserNotFoundException();

        Fandom fandom = fandomRepository.getFandomByName(fandomName);
        if (fandom == null) throw new FandomNotFoundException();

        // Create the relationship
        UserToFandom relationship = new UserToFandom(user, fandom, type);

        return userToFandomRepository.save(relationship);

    }
}
