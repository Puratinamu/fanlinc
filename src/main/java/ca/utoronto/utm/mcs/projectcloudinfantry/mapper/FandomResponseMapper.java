package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.FandomResponse;
import org.springframework.stereotype.Component;

@Component
public class FandomResponseMapper {
    public FandomResponse toFandomResponse(Fandom fandom) {
        FandomResponse response = new FandomResponse();
        response.setOidFandom(fandom.getOidFandom());
        response.setName(fandom.getName());
        response.setDescription(fandom.getDescription());
        return response;
    }

}
