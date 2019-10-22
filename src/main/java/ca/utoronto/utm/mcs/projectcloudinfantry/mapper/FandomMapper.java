package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FandomMapper {

    public Fandom toFandom(Map<String, Object> body) {
        Fandom fandom = new Fandom();
        fandom.setName(MapperUtils.toEmptyIfNull(body.get("name")));
        fandom.setDescription(MapperUtils.toEmptyIfNull(body.get("description")));
        return fandom;
    }

}
