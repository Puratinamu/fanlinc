package ca.utoronto.utm.mcs.projectcloudinfantry.request;

public abstract class ContentRequest {
    private Long oidUser;
    private Long oidFandom;

    public Long getOidUser() {return oidUser;}
    public Long getOidFandom() {return oidFandom;}
    public void setOidUser(Long oid) {this.oidUser = oid;}
    public void setOidFandom(Long oid) {this.oidFandom = oid;}
}
