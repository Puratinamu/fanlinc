package ca.utoronto.utm.mcs.projectcloudinfantry.repository;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class UserContactInfoResult {

    private User contact;

    public User getUser() {
        return contact;
    }

    public void setUser(User contact) {
        this.contact = contact;
    }
}
