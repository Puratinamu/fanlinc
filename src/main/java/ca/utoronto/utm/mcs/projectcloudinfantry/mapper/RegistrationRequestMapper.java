package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class RegistrationRequestMapper {

    public RegistrationRequest toRegisrationRequest(Map<String, Object> requestBody) {
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail((String) requestBody.get("email"));
        request.setUsername((String) requestBody.get("username"));
        request.setPassword((String) requestBody.get("password"));
        request.setDescription((String) requestBody.get("description"));
        request.setFandoms((List<Map<String, Object>>) requestBody.get("fandoms"));
        return request;
    }

}
