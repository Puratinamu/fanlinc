package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.request.UserToFandomRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RelationshipRequestMapper {

    public UserToFandomRequest toRelationshipRequest(Map<String, Object> body) {
        UserToFandomRequest fandom = new UserToFandomRequest();
        fandom.setOidFandom(MapperUtils.toLong(body.get("oidFandom")));
        fandom.setLevel(MapperUtils.toEmptyIfNull(body.get("level")));
        return fandom;
    }

}