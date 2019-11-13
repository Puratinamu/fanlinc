package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface UserToContactRepository extends Neo4jRepository<UserToFandom, Long> {

    @Query("MATCH (u:User)-[r:CONTACT_WITH]->(c:User) WHERE ID(u)={oidUser} RETURN c as contact")
    List<UserContactInfoResult> getUserContactsByOidUser(Long oidUser);

}
