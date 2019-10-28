package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.FandomObject;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FandomObjectMapper {

    public FandomObject toFandomObject(Map<String, Object> body) {
        FandomObject fandom = new FandomObject();
        fandom.setOidFandom(((Integer) body.get("oidFandom")).longValue());
        fandom.setLevel((String) body.get("level"));
        return fandom;
    }

}