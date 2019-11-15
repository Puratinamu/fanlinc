package ca.utoronto.utm.mcs.projectcloudinfantry.datafactory;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;

import java.util.ArrayList;

public class UserFactory {
    public static User CreateUser(String username, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setFandoms(new ArrayList<>());
        user.setDescription("I am a user");
        return user;
    }
}
