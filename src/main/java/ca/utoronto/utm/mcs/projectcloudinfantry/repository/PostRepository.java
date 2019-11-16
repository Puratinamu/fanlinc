package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {

    @Query(value = "MATCH (u:User)-[:BELONGS_TO]->(f:Fandom) " +
            "MATCH (p:Post)-[:POSTED_TO]->(f) " +
            "WHERE ID(u)={oidUser} " +
            "RETURN p")
    List<Post> getPostFeedByOidUser(Long oidUser);

}
