package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FandomMapper {

    public Fandom toFandom(Map<String, Object> body) {
        Fandom fandom = new Fandom();
        try {
            fandom.setName((String) body.get("name"));
        }
        catch (Exception e) {
            fandom.setName("");
        }
        try {
            fandom.setDescription((String) body.get("description"));
        }
        catch (Exception e){
            fandom.setDescription("");
        }
        return fandom;
    }

}
