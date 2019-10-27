package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.LoginRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;

public interface UserService {

    User registerUser(RegistrationRequest request);
    String loginUser(LoginRequest request);
    User getUserByUsername(User user);
}
