package ca.utoronto.utm.mcs.projectcloudinfantry.request;

public class AddContactRequest {

    private Long oidUser;
    private Long contactOidUser;

    public Long getOidUser() {
        return oidUser;
    }

    public void setOidUser(Long oidUser) {
        this.oidUser = oidUser;
    }

    public Long getContactOidUser() {
        return contactOidUser;
    }

    public void setContactOidUser(Long contactOidUser) {
        this.contactOidUser = contactOidUser;
    }
}
