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

    public User toUser(RegistrationRequest requestBody) {
        User user = new User();
        // Set user parameters except fandoms
        user.setEmail(MapperUtils.toEmptyIfNull(requestBody.getEmail()));
        user.setUsername(MapperUtils.toEmptyIfNull(requestBody.getUsername()));
        user.setPassword(MapperUtils.toEmptyIfNull(requestBody.getPassword()));
        user.setDescription(MapperUtils.toEmptyIfNull(requestBody.getDescription()));
        return user;
    }

}
