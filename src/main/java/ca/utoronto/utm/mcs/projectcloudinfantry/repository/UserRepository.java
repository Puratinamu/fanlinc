package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface UserRepository extends Neo4jRepository<User, Long>  {
    // Expect each user to have a unique email
    User findByEmail(String email);
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);

    @Query("match(f:Fandom)-[:HAS_CHAT {relationship:{relationshipType}}]->(c:ChatRoom)" +
            "match(u:User)-[:IN_CHAT{relationship:{relationshipType}}]->(c) "+
            "WHERE ID(f) = {oidFandom} and ID(u) = {oidUser}" +
            "return u")
    User findUserByFandomChatRoom(long oidUser, String relationshipType, long oidFandom);
}
