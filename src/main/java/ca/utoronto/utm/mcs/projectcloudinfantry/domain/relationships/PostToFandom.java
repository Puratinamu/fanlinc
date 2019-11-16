package ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "POSTED_TO")
public class PostToFandom {

    @Id
    @GeneratedValue
    private Long oidPostToFandom;

    @StartNode
    private Post post;

    @EndNode
    private Fandom fandom;

    private RelationshipLevel relationshipLevel;

    public Long getOidPostToFandom() {
        return oidPostToFandom;
    }

    public void setOidPostToFandom(Long oidPostToFandom) {
        this.oidPostToFandom = oidPostToFandom;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Fandom getFandom() {
        return fandom;
    }

    public void setFandom(Fandom fandom) {
        this.fandom = fandom;
    }

    public RelationshipLevel getRelationshipLevel() {
        return relationshipLevel;
    }

    public void setRelationshipLevel(RelationshipLevel relationshipLevel) {
        this.relationshipLevel = relationshipLevel;
    }
}
