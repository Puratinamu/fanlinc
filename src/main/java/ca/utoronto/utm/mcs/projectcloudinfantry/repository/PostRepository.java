package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends Neo4jRepository<Post, Long> {

    @Query("MATCH (u:User)-[:BELONGS_TO]->(f:Fandom)\n" +
            "WHERE ID(u)={oidUser}\n" +
            "MATCH (p:Post)-[:POSTED_TO]->(f)\n" +
            "RETURN p")
    List<Post> getPostFeed(Long oidUser);
}
