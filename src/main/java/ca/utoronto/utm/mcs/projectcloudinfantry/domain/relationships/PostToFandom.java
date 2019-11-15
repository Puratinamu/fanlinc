package ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "POSTED_TO")
public class PostToFandom {

    @Id
    @GeneratedValue
    private String oidPostToFandom;

    @StartNode
    private Post post;

    @EndNode
    private Fandom fandom;

    private RelationshipLevel relationshipLevel;

}
