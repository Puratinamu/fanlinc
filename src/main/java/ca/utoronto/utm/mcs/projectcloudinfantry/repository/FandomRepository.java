package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FandomRepository extends Neo4jRepository<Fandom, UUID>  {

    @Query("MATCH (f: Fandom) WHERE f.oidFandom={oidFandom} RETURN f")
    Fandom getFandom(@Param("oidFandom") String oidFandom);

    // @Query("MATCH (f: Fandom {name:{fandom_name}}) RETURN f")
    Fandom getFandomByName(String name);
}
