package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.RegistrationResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RegistrationResponseMapper {

    public RegistrationResponse toRegisrationReponse(User user) {
        RegistrationResponse response = new RegistrationResponse();
        response.setOidUser(user.getOidUser());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setDescription(user.getDescription());
        List<Long> fandomIds = new ArrayList<>();
        // Get list of ids from fandoms
        for (Fandom f : user.getFandoms()) {
            fandomIds.add(f.getOidFandom());
        }
        response.setFandoms(fandomIds);
        return response;
    }
}
