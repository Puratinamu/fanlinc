package ca.utoronto.utm.mcs.projectcloudinfantry.domain;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@NodeEntity
public class Fandom {

    @Id
    private UUID oidFandom;

    private String name;

    private String description;

    @Relationship(type = "BELONGS_TO", direction = Relationship.INCOMING)
    private List<User> members;

    @Relationship(type = "POSTED_TO", direction = Relationship.INCOMING)
    private List<Post> posts;

    @CreatedDate
    private Date creationTimestamp;

    private Date lastUpdateTimestamp;

    public Fandom(String name, String description){
        this.name = name;
        this.description = description;
    }

    public UUID getOidFandom() {
        return oidFandom;
    }

    public void setOidFandom(UUID oidFandom) {
        this.oidFandom = oidFandom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
