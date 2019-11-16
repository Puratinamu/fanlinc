package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.PostToFandom;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PostToFandomRepository extends Neo4jRepository<PostToFandom, Long> {

    @Query(value = "MATCH (p:Post)-[r:POSTED_TO]->(f:Fandom) WHERE ID(p) = {oidPost} AND ID(f) = {oidFandom} REUTRN r")
    PostToFandom findByOidPostAndOidFandom(Long oidPost, Long oidFandom);

}
