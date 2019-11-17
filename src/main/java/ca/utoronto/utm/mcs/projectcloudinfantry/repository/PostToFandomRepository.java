package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.PostToFandom;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostToFandomRepository extends Neo4jRepository<PostToFandom, Long> {

    @Query(value = "MATCH (p:Post)-[r:POSTED_TO]->(f:Fandom) WHERE ID(p) = {oidPost} AND ID(f) = {oidFandom} RETURN r")
    PostToFandom findByOidPostAndOidFandom(@Param("oidPost") Long oidPost, @Param("oidFandom") Long oidFandom);

    @Query(value = "MATCH (p:Post)-[:POSTED_TO]->(f:Fandom) WHERE ID(p) = {oidPost} RETURN ID(f)")
    Long findOidFandomByOidPost(@Param("oidPost") Long oidPost);
}
