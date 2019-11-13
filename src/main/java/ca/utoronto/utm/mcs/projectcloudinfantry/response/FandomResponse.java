package ca.utoronto.utm.mcs.projectcloudinfantry.response;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;


public class FandomResponse {

    private Long oidFandom;
    private String name;
    private String description;
    // TODO: list of posts and members

    public Long getOidFandom() {return oidFandom;}
    public void setOidFandom(Long oid) {this.oidFandom = oid;}
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}
