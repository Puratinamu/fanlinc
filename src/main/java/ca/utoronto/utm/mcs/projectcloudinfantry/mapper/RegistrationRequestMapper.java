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
        request.setEmail(MapperUtils.toEmptyIfNull(requestBody.get("email")));
        request.setUsername(MapperUtils.toEmptyIfNull(requestBody.get("username")));
        request.setPassword(MapperUtils.toEmptyIfNull(requestBody.get("password")));
        request.setDescription(MapperUtils.toEmptyIfNull(requestBody.get("description")));
        request.setFandoms(MapperUtils.objToListOfObj(requestBody.get("fandoms")));
        return request;
    }

}
