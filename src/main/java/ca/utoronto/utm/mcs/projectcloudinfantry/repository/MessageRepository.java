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
}
