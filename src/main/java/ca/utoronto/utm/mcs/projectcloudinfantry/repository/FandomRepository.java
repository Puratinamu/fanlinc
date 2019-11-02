package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FandomRepository extends Neo4jRepository<Fandom, Long>  {

    Fandom getFandomByName(String name);

    @Query("MATCH (u:User)-[:BELONGS_TO]->(f) WHERE ID(u)={oidUser} RETURN f")
    List<Fandom> getFandomsByOidUser(Long oidUser);

    @Query("MATCH (u:User)-[r:BELONGS_TO]->(f:Fandom)\n" +
            " WHERE ID(u)={oidUser}\n" +
            " RETURN f as fandom, r.relationship as relationship")
    List<FandomInfoResult> getFandomsAndRelationshipsByOidUser(Long oidUser);
}

