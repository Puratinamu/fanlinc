package ca.utoronto.utm.mcs.projectcloudinfantry.domain.content;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.UUID;

@NodeEntity
public abstract class Content {

    @Id
    private UUID oidContent;

    @Relationship(value = "HAS_CONTENT", direction = Relationship.INCOMING)
    private Post post;

    @CreatedDate
    private Date creationTimestamp;

    private Date lastUpdateTimestamp;

    public UUID getOidContent() {
        return oidContent;
    }

    public void setOidContent(UUID oidContent) {
        this.oidContent = oidContent;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Date getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Date lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
