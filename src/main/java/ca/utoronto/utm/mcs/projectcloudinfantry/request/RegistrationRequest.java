package ca.utoronto.utm.mcs.projectcloudinfantry.request;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;

import java.util.ArrayList;
import java.util.List;

public class RegistrationRequest {

    private String oidUser;
    private String email;
    private String username;
    private String password;
    private String description;
    private List<String> fandoms;

    public String getOidUser() {
        return oidUser;
    }

    public void setOidUser(String oidUser) {
        this.oidUser = oidUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getFandoms() {
        return fandoms;
    }

    public void setFandoms(List<String> fandoms) {
        this.fandoms = fandoms;
    }
}
