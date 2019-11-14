package ca.utoronto.utm.mcs.projectcloudinfantry.domain;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.Content;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@NodeEntity
public class Post {

    @Id
    private Long oidPost;

    @Relationship(type = "HAS_CONTENT")
    private Content content;

    @Relationship(type = "POSTED", direction = Relationship.INCOMING)
    private User creator;

    @Relationship(type = "POSTED_TO")
    private Fandom fandom;

    private String title;

    @CreatedDate
    private Date creationTimestamp;

    private Date lastUpdateTimestamp;

    public Long getOidPost() {
        return oidPost;
    }

    public void setOidPost(Long oidPost) {
        this.oidPost = oidPost;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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

    public Fandom getFandom() {
        return fandom;
    }

    public void setFandom(Fandom fandom) {
        this.fandom = fandom;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
