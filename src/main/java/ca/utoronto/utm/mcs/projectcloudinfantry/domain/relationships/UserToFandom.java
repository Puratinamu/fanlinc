package ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.id.UuidStrategy;

import java.util.UUID;

@RelationshipEntity(type = "BELONGS_TO")
public class UserToFandom {

    @Id @GeneratedValue
    private Long oidUserToFandom;

    private String relationship;

    @StartNode
    private User user;

    @EndNode
    private Fandom fandom;

    public UserToFandom(User user, Fandom fandom, String type){
        this.setRelationship(type);
        this.setFandom(fandom);
        this.setUser(user);
    }

    public Long getOidUserToFandom() {
        return oidUserToFandom;
    }

    public void setOidUserToFandom(Long oidUserToFandom) {
        this.oidUserToFandom = oidUserToFandom;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Fandom getFandom() {
        return fandom;
    }

    public void setFandom(Fandom fandom) {
        this.fandom = fandom;
    }
}
