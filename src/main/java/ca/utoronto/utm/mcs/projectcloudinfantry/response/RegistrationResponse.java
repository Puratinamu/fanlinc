package ca.utoronto.utm.mcs.projectcloudinfantry.response;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;

import java.util.List;

public class RegistrationResponse {

    private String oidUser;
    private String email;
    private String username;
    private String description;
    private List<String> fandoms;

    public RegistrationResponse(RegistrationRequest request) {
        this.oidUser = request.getOidUser();
        this.email = request.getEmail();
        this.username = request.getUsername();
        this.description = request.getDescription();
        this.fandoms = request.getFandoms();
    }

    public String getOidUser() {
        return oidUser;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getFandoms() {
        return fandoms;
    }
}
