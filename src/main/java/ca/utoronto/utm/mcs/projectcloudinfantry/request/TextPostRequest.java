package ca.utoronto.utm.mcs.projectcloudinfantry.request;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.RelationshipLevel;

public class TextPostRequest {

    private Long oidCreator;
    private Long oidFandom;
    private String text;
    private String title;
    private RelationshipLevel relationshipLevel;

    public Long getOidCreator() {
        return oidCreator;
    }

    public void setOidCreator(Long oidCreator) {
        this.oidCreator = oidCreator;
    }

    public Long getOidFandom() {
        return oidFandom;
    }

    public void setOidFandom(Long oidFandom) {
        this.oidFandom = oidFandom;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RelationshipLevel getRelationshipLevel() {
        return relationshipLevel;
    }

    public void setRelationshipLevel(RelationshipLevel relationshipLevel) {
        this.relationshipLevel = relationshipLevel;
    }
}
