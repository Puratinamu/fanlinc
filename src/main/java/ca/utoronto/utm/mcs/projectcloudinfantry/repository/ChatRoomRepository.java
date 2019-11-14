package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.ChatRoom;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository  extends Neo4jRepository<ChatRoom, Long>{
    @Query("match(f:Fandom)-[:HAS_CHAT {relationship:{fandomRelationship}}]->(c:ChatRoom)" +
            "WHERE ID(f) = {oidFandom}" +
            "return c")
    ChatRoom getChatRoomForFandomByInterestLevel(Long oidFandom, String fandomRelationship);
}
