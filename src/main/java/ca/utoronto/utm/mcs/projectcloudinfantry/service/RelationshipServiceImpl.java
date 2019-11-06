package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.BelongsToRelationshipAlreadyExists;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
    public void addUserToFandom(Long iodUser, Long oidFandom, String type) {
        // Get the fandom and user.
        Optional<User> user = userRepository.findById(iodUser);
        if (!user.isPresent()) throw new UserNotFoundException();
        User foundUser = user.get();

        Optional<Fandom> fandom = fandomRepository.findById(oidFandom);
        if (!fandom.isPresent()) throw new FandomNotFoundException();
        Fandom foundFandom = fandom.get();

        if (foundUser.getFandoms() == null) {
            foundUser.setFandoms(new ArrayList<Fandom>());
        }

        // Try to get the relationship if it already exists
        UserToFandom relationship = userToFandomRepository.findByUserIdAndFandomID(iodUser, oidFandom);

        // If not exists, create it
        if (relationship == null) {
            // Create the relationship
            relationship = new UserToFandom(foundUser, foundFandom, type);
        }

        // Update the relationship strength type if its different
        relationship.setRelationship(type);

        userToFandomRepository.save(relationship);
    }
}
