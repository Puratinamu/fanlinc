package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserMapper {


    public User toUser(Map<String, Object> requestBody) {
        User user = new User();
        user.setEmail(requestBody.get("email").toString());
        user.setUsername(requestBody.get("username").toString());
        user.setPassword(requestBody.get("password").toString());
        user.setDescription(requestBody.get("description").toString());
        user.setFandoms((List<Fandom>) requestBody.get("fandoms"));
        return user;
    }

}
