package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToContact;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface UserToContactRepository extends Neo4jRepository<UserToContact, Long> {

    @Query("MATCH (u:User)-[r:CONTACT_WITH]->(c:User) WHERE ID(u)={oidUser} RETURN c as contact")
    List<UserContactInfoResult> getUserContactsByOidUser(Long oidUser);

    @Query("MATCH (u:User)-[r:CONTACT_WITH]->(c:User) WHERE ID(u)={oidUser} and ID(c)={contactOidUser} " +
            "RETURN u as user, c as contact")
    UserContactInfoResult findByUserIdAndUserContactId(Long oidUser, Long contactOidUser);
}
