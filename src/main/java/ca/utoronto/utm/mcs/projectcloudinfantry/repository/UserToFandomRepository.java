package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserToFandomRepository extends Neo4jRepository<UserToFandom, Long> {

    // Get the relationship
    UserToFandom getRelationship(String username, Long oidFandom);
}
