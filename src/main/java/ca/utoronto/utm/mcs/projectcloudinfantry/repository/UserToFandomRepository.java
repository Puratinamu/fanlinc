package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserToFandomRepository extends Neo4jRepository<UserToFandom, Long> {

    @Query("MATCH (:User {username:{username}})-[r:BELONGS_TO]->(:Fandom {name: {fandomName}}) RETURN r")
    UserToFandom findByUserAndFandomNames(String username, String fandomName);

    //@Query("MATCH (:User {name:{username}})-[r:BELONGS_TO]->(:Fandom {name: {fandomName}}) RETURN r")
    UserToFandom findByUserAndFandom(User user, Fandom fandom);

    @Query("MATCH (u:User)-[r:BELONGS_TO]->(f:Fandom) WHERE ID(u) = {userId} and ID(f) = {fandomId} RETURN r")
    UserToFandom findByUserIdAndFandomID(Long userId, Long fandomId);
}
