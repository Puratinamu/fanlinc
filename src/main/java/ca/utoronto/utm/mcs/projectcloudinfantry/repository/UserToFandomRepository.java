package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserToFandomRepository extends Neo4jRepository<UserToFandom, Long> {

    @Query("MATCH (:User {username:{username}})-[r:BELONGS_TO]->(:Fandom {name: {fandomName}}) RETURN r")
    UserToFandom findByUserAndFandomNames(String username, String fandomName);

    //@Query("MATCH (:User {name:{username}})-[r:BELONGS_TO]->(:Fandom {name: {fandomName}}) RETURN r")
    UserToFandom findByUserAndFandom(User user, Fandom fandom);
}
