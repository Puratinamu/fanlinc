package ca.utoronto.utm.mcs.projectcloudinfantry.response;

public class ContentResponse {
    private Long oidContent;
    private Long oidUser;
    private Long oidFandom;

    public Long getOidContent() {return this.oidContent;}
    public Long getOidUser() {return oidUser;}
    public Long getOidFandom() {return oidFandom;}
    public void setOidContent(Long oid) {this.oidContent = oid;}
    public void setOidUser(Long oid) {this.oidUser = oid;}
    public void setOidFandom(Long oid) {this.oidFandom = oid;}
}
