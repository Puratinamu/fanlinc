package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.request.RelationshipRequest;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RelationshipRequestMapper {

    public RelationshipRequest toRelationshipRequest(Map<String, Object> body) {
        RelationshipRequest fandom = new RelationshipRequest();
        fandom.setOidFandom(((Integer) body.get("oidFandom")).longValue());
        fandom.setLevel((String) body.get("level"));
        return fandom;
    }

}