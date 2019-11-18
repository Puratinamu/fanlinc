package ca.utoronto.utm.mcs.projectcloudinfantry.repository.queryresult;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class FandomInfoResult {

    private Fandom fandom;
    private String relationship;

    public void setFandom(Fandom fandom) {
        this.fandom = fandom;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Fandom getFandom() {
        return fandom;
    }

    public String getRelationship() {
        return relationship;
    }


}
