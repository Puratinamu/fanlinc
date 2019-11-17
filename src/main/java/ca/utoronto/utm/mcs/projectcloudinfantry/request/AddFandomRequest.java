package ca.utoronto.utm.mcs.projectcloudinfantry.request;

public class AddFandomRequest {

    private String name;

    private String description;

    private Long creator;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }
}
