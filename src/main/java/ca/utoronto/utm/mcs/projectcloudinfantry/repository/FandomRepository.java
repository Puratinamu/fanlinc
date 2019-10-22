package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FandomRepository extends Neo4jRepository<Fandom, Long>  {
    /*
        @Query("MATCH (f: Fandom) WHERE f.oidFandom={oidFandom} RETURN f")
        Fandom getFandom(UUID oid);
    */
    Fandom getFandomByName(String name);
}