package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserMapper {

    public User toUser(Map<String, Object> requestBody) {
        User user = new User();
        user.setEmail(MapperUtils.toEmptyIfNull(requestBody.get("email")));
        user.setUsername(MapperUtils.toEmptyIfNull(requestBody.get("username")));
        user.setPassword(MapperUtils.toEmptyIfNull(requestBody.get("password")));
        user.setDescription(MapperUtils.toEmptyIfNull(requestBody.get("description")));
        // List of fandom id's
        user.setFandoms((List<Fandom>) requestBody.get("fandoms"));
        return user;
    }

}
