package ca.utoronto.utm.mcs.projectcloudinfantry.response;


import ca.utoronto.utm.mcs.projectcloudinfantry.response.ContentResponse;
public class TextPostResponse extends ContentResponse {
    private String text;

    public Long getOidContent() {return super.getOidContent();}
    public void setOidContent(Long oid) {super.setOidContent(oid);}
    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    public Long getOidUser() {return super.getOidUser();}
    public Long getOidFandom() {return super.getOidFandom();}
    public void setOidUser(Long oid) {super.setOidUser(oid);}
    public void setOidFandom(Long oid) {super.setOidFandom(oid);}
}
