package ca.utoronto.utm.mcs.projectcloudinfantry.controller;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.service.FandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class FandomController {

    @Autowired
    private
    FandomService fandomService;

    @RequestMapping(value = "/api/getFandom", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Optional<Fandom> getFandom(@RequestBody Fandom fandom) {return fandomService.getFandom(fandom);}

    @RequestMapping(value = "/api/getFandoms", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Collection<Fandom> getAll() {return fandomService.getFandoms();}

    @RequestMapping(value = "/api/getFandomByName", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Fandom getFandomByName(@RequestBody Fandom fandom) {return fandomService.getFandomByName(fandom);}

    @RequestMapping(value = "/api/addFandom", method = PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Fandom addFandom(@RequestBody Fandom fandom) {
        return fandomService.addFandom(fandom);
    }
}
