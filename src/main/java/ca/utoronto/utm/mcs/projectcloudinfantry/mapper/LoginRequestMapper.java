package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.request.LoginRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class LoginRequestMapper {
    public LoginRequest toLoginRequest(Map<String, Object> requestBody) {
        LoginRequest request = new LoginRequest();
        request.setEmail((String) requestBody.get("email"));
        request.setPassword((String) requestBody.get("password"));
        return request;
    }
}
