package ca.utoronto.utm.mcs.projectcloudinfantry.response;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;

import java.util.List;

public class UserContactsResponse {

    private List<UserContactInfo> contacts;

    public UserContactsResponse(List<UserContactInfo> contacts) {
        this.contacts = contacts;
    }

    public List<UserContactInfo> getContacts() {
        return contacts;
    }

    public void setContacts(List<UserContactInfo> contacts) {
        this.contacts = contacts;
    }
}
