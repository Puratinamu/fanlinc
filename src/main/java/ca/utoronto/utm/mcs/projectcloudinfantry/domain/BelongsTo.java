package ca.utoronto.utm.mcs.projectcloudinfantry.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "BELONGS_TO")
public class BelongsTo {

    @StartNode
    private User user;

    @EndNode
    private Fandom fandom;
}
