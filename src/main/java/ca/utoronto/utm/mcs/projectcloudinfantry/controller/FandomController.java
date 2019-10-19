package ca.utoronto.utm.mcs.projectcloudinfantry.controller;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.service.FandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class FandomController {

    @Autowired
    private
    FandomService fandomService;

    @RequestMapping(value = "/api/getFandom/{id}", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Fandom getFandom(@PathVariable("id") UUID oid) {
        return fandomService.getFandom(oid);
    }

    @RequestMapping(value = "/api/getFandomByName/{name}", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Fandom getFandom(@PathVariable("name") String name) {
        return fandomService.getFandomByName(name);
    }

    @RequestMapping(value = "/api/getFandoms", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Collection<Fandom> getAll() {
        return fandomService.getAll();
    }

    @RequestMapping(value = "/api/addFandom", method = PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Fandom addFandom(Fandom fandom) {
        return fandomService.addFandom(fandom);
    }
}
