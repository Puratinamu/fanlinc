package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.request.AddContactRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AddContactRequestMapper {

    public AddContactRequest toAddContactRequest(Map<String, Object> requestBody) {
        AddContactRequest request = new AddContactRequest();
        request.setOidUser(MapperUtils.toLong(requestBody.get("oidUser")));
        request.setContactOidUser(MapperUtils.toLong(requestBody.get("contactOidUser")));
        return request;
    }

}
