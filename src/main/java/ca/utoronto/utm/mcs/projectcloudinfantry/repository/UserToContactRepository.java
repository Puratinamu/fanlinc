package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToContact;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface UserToContactRepository extends Neo4jRepository<UserToContact, Long> {

    @Query("MATCH (u:User)-[r:CONTACT_WITH]->(c:User) WHERE ID(u)={oidUser} RETURN c as contact")
    List<UserContactInfoResult> getUserContactsByOidUser(Long oidUser);

    @Query("MATCH (u:User)-[r:CONTACT_WITH]->(c:User) WHERE ID(u)={oidUser} and ID(c)={contactOidUser} RETURN c")
    UserToContact findByUserIdAndUserContactId(Long oidUser, Long contactOidUser);
}
