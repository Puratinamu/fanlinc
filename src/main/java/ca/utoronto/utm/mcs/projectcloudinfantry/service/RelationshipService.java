package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface RelationshipService {

    void addUserToFandom(Map<String, Object> body);

}
