package ca.utoronto.utm.mcs.projectcloudinfantry.request;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;

import java.util.ArrayList;
import java.util.List;

public class RegistrationRequest {

    private String oidUser;
    private String email;
    private String username;
    private String description;
    private List<Fandom> fandomList = new ArrayList<>();

    public RegistrationRequest(User user) {
        this.oidUser = user.getOidUser().toString();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.description = user.getDescription();
        this.fandomList = user.getFandoms();
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

    public List<Fandom> getFandomList() {
        return fandomList;
    }
}
