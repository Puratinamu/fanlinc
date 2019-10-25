package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.security.BcryptUtils;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class UserMapper {

    public User toUser(RegistrationRequest requestBody, FandomRepository fandomRepository) {
        User user = new User();
        // Bcrypt password to store in db
        String password = requestBody.getPassword();
        password = BcryptUtils.encodePassword(password);
        // Create list of Fandoms from list of Fandom Ids
        List<String> fandomIds = requestBody.getFandomIds();
        List<Fandom> fandoms = new ArrayList<>();
        for (String f : fandomIds) {
            Optional<Fandom> optionalFandom;
            try {
                optionalFandom = fandomRepository.findById(Long.valueOf(f));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Fandom ID '" + f + "' is not a long type");
            }
            // If fandom does not exist, throw exception
            if (!optionalFandom.isPresent()) {
                throw new FandomNotFoundException("Fandom with ID '" + f + "' not found");
            }
            Fandom fandom = optionalFandom.get();
            fandoms.add(fandom);
        }

        // Set user parameters
        user.setEmail(MapperUtils.toEmptyIfNull(requestBody.getEmail()));
        user.setUsername(MapperUtils.toEmptyIfNull(requestBody.getUsername()));
        user.setPassword(MapperUtils.toEmptyIfNull(password));
        user.setDescription(MapperUtils.toEmptyIfNull(requestBody.getDescription()));
        user.setFandoms(fandoms);
        // Set date of creation
        Date date = new Date();
        user.setCreationTimestamp(date);
        user.setLastLoginTimestamp(date);
        user.setLastUpdateTimestamp(date);

        return user;
    }

}
