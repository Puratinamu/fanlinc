package ca.utoronto.utm.mcs.projectcloudinfantry.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistrationRequest {

    private String email;
    private String username;
    private String password;
    private String description;
    private List<Map<String, Object>> fandoms = new ArrayList<>();

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

    public List<Map<String, Object>> getFandoms() {
        return fandoms;
    }

    public void setFandoms(List<Map<String, Object>> fandoms) {
        this.fandoms = fandoms;
    }
}
