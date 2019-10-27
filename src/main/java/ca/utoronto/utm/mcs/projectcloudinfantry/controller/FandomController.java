package ca.utoronto.utm.mcs.projectcloudinfantry.controller;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.FandomMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.service.FandomService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class FandomController {

    private FandomService fandomService;
    private FandomMapper fandomMapper;

    public FandomController(FandomService fandomService, FandomMapper fandomMapper) {
        this.fandomService = fandomService;
        this.fandomMapper = fandomMapper;
    }

    @RequestMapping(value = "/api/v1/getFandom", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Optional<Fandom> getFandom(@RequestBody Map<String, String> body, HttpServletResponse res) {
        return fandomService.getFandom(Long.parseLong(body.get("oidFandom")));
    }

    @RequestMapping(value = "/api/v1/getFandoms", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Collection<Fandom> getAll() {
        return fandomService.getFandoms();
    }

    @RequestMapping(value = "/api/v1/getFandomByName", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Fandom getFandomByName(@RequestBody Fandom fandom) {return fandomService.getFandomByName(fandom);}

    @RequestMapping(value = "/api/v1/addFandom", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Fandom addFandom(@RequestBody Map<String, Object> body) {
        Fandom fandom = fandomMapper.toFandom(body);
        return fandomService.addFandom(fandom);
    }
}
