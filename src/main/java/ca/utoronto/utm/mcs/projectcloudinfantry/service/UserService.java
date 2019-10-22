package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.exception.NotAuthorizedException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserAlreadyExistsException;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.LoginRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;

public interface UserService {

    void registerUser(RegistrationRequest request);
    void loginUser(LoginRequest request);
}
