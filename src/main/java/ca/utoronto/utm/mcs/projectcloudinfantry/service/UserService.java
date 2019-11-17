package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.AddContactRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.LoginRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.LoginResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.ProfileResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.UserContactsResponse;

public interface UserService {

    User registerUser(RegistrationRequest request);
    LoginResponse loginUser(LoginRequest request);
    User getUserByUsername(User user);
    ProfileResponse getProfile(String oidUser);
    void addContact(AddContactRequest request);
    UserContactsResponse getContacts(Long oidUser);
}