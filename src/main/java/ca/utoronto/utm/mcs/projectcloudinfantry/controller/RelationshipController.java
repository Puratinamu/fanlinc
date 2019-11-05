package ca.utoronto.utm.mcs.projectcloudinfantry.controller;

import ca.utoronto.utm.mcs.projectcloudinfantry.service.RelationshipService;
import ca.utoronto.utm.mcs.projectcloudinfantry.token.TokenService;
import org.springframework.http.HttpHeaders;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class RelationshipController {

    private RelationshipService relationshipService;
    private TokenService tokenService;

    public RelationshipController (RelationshipService relationshipService, TokenService tokenService) {
        this.relationshipService = relationshipService;
        this.tokenService = tokenService;
    }

    @RequestMapping(value="api/v1/updateFandomRelationship", method = PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity updateFandomRelationship(@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> body) {
        try {
            Long oidUser = MapperUtils.toLong(body.get("oidUser"));
            Long oidFandom = MapperUtils.toLong(body.get("oidFandom"));
            String relationship = (String) body.get("relationship");
            tokenService.authenticate(headers.getFirst("jwt"), oidUser);
            relationshipService.addUserToFandom(oidUser, oidFandom, relationship);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
