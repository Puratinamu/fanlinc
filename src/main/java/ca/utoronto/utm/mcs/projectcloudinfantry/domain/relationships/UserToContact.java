package ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "CONTACT_WITH")
public class UserToContact {

    @Id
    @GeneratedValue
    private Long oidUserToContact;

    @Property
    @StartNode
    private User user;

    @Property
    @EndNode
    private User contact;

    public UserToContact(User user, User contact){
        this.setUser(user);
        this.setContact(contact);
    }

    public Long getOidUserToContact() {
        return oidUserToContact;
    }

    public void setOidUserToContact(Long oidUserToContact) {
        this.oidUserToContact = oidUserToContact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getContact() {
        return contact;
    }

    public void setContact(User contact) {
        this.contact = contact;
    }


}