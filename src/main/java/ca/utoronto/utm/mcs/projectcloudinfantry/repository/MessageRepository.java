package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Message;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends Neo4jRepository<Message, Long> {
    @Query("MATCH(f:Fandom)-[:HAS_CHAT {relationship:{fandomInterestLevel}} ]->(c:ChatRoom)\n" +
            "MATCH  (m:Message)-[:FROM_CHAT]->(c)\n" +
            "WHERE ID(f) = {fandomId}\n" +
            "RETURN collect(m)")
    List<Message> getMessagesFromAFandomChatRoom(Long fandomId, String fandomInterestLevel);

    @Query("MATCH(u1:User)-[:IN_CHAT {relationship:'dm'}]->(c:ChatRoom)" +
            "MATCH(u2:User)-[:IN_CHAT]->(c)" +
            "MATCH (m:Message)-[:FROM_CHAT]->(c)" +
            "return collect(m)")
    List<Message> getMessagesForDM(Long fromId, Long toId);

}
