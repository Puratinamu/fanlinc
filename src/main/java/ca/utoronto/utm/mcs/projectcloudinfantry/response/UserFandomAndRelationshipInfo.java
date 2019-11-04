package ca.utoronto.utm.mcs.projectcloudinfantry.response;

public class UserFandomAndRelationshipInfo {

    private Long oidFandom;
    private String name;
    private String description;
    private String relationship;

    public Long getOidFandom() {
        return oidFandom;
    }

    public void setOidFandom(Long oidFandom) {
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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

}
