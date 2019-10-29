package ca.utoronto.utm.mcs.projectcloudinfantry.response;

import java.util.List;

public class RegistrationResponse {

    private Long oidUser;
    private String email;
    private String username;
    private String description;
    private List<Long> fandoms;

    public Long getOidUser() {
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

    public List<Long> getFandoms() {
        return fandoms;
    }

    public void setOidUser(Long oidUser) {
        this.oidUser = oidUser;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFandoms(List<Long> fandoms) {
        this.fandoms = fandoms;
    }
}
