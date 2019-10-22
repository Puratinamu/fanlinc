package ca.utoronto.utm.mcs.projectcloudinfantry.controller;

import ca.utoronto.utm.mcs.projectcloudinfantry.service.RelationshipService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class RelationshipController {

    private RelationshipService relationshipService;

    public RelationshipController (RelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

    @RequestMapping(value="api/v1/updateFandomRelationship", method = PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public void updateFandomRelationship(@RequestBody Map<String, Object> body) {
        Long oidUser = Long.parseLong(((Integer) body.get("oidUser")).toString());
        Long oidFandom = Long.parseLong(((Integer) body.get("oidFandom")).toString());
        String relationship = (String) body.get("relationship");

        relationshipService.addUserToFandom(oidUser, oidFandom, relationship);
    }
}
