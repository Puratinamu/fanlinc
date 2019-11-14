package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.FandomToChatRoom;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FandomToChatRoomRepository extends Neo4jRepository<FandomToChatRoom, Long> {
}
