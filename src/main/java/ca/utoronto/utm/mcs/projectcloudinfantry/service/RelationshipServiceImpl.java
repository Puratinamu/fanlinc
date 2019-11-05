package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserToFandomRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
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
    public void addUserToFandom(Map<String, Object> body) {
        Long oidUser = Long.valueOf(((Integer) body.get("oidUser")).toString());
        Long oidFandom = Long.valueOf(((Integer) body.get("oidFandom")).toString());
        String type = (String) body.get("relationship");

        // Get the fandom and user.
        Optional<User> user = userRepository.findById(oidUser);
        if (!user.isPresent()) throw new UserNotFoundException();
        User foundUser = user.get();

        Optional<Fandom> fandom = fandomRepository.findById(oidFandom);
        if (!fandom.isPresent()) throw new FandomNotFoundException();
        Fandom foundFandom = fandom.get();

        if (foundUser.getFandoms() == null) {
            foundUser.setFandoms(new ArrayList<Fandom>());
        }

        // Try to get the relationship if it already exists
        UserToFandom relationship = userToFandomRepository.findByUserIDAndFandomID(oidUser, oidFandom);

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
