package ca.utoronto.utm.mcs.projectcloudinfantry.request;


public class TextPostRequest extends ContentRequest{
    private String text;

    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    public Long getOidUser() {return super.getOidUser();}
    public Long getOidFandom() {return super.getOidFandom();}
    public void setOidUser(Long oid) {super.setOidUser(oid);}
    public void setOidFandom(Long oid) {super.setOidFandom(oid);}
}
