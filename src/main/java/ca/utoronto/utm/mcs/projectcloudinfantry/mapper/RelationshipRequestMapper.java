package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.request.RelationshipRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RelationshipRequestMapper {

    public RelationshipRequest toRelationshipRequest(Map<String, Object> body) {
        RelationshipRequest fandom = new RelationshipRequest();
        fandom.setOidFandom(MapperUtils.toLong(body.get("oidFandom")));
        fandom.setLevel(MapperUtils.toEmptyIfNull(body.get("level")));
        return fandom;
    }

}