package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FandomRepository extends Neo4jRepository<Fandom, Long>  {

    @Query("MATCH (f: Fandom) WHERE f.oidFandom={oidFandom} RETURN f")
    Fandom getFandom(@Param("oidFandom") UUID oidFandom);

    @Query("MATCH (f: Fandom) WHERE f.name={name} RETURN f")
    Fandom getFandomByName(@Param("name") String name);
}
