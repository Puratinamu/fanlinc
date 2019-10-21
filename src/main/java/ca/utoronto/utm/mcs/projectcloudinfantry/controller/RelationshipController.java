package ca.utoronto.utm.mcs.projectcloudinfantry.controller;

import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserToFandomRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.service.RelationshipService;
import ca.utoronto.utm.mcs.projectcloudinfantry.service.RelationshipServiceImpl;
import ca.utoronto.utm.mcs.projectcloudinfantry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelationshipController {
    @Autowired
    private RelationshipService relationshipService;


}
