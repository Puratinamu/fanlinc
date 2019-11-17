package ca.utoronto.utm.mcs.projectcloudinfantry.controller;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.FandomMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.FandomResponseMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.FandomResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.service.FandomService;
import ca.utoronto.utm.mcs.projectcloudinfantry.token.TokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class FandomController {

    private FandomService fandomService;
    private FandomMapper fandomMapper;
    private TokenService tokenService;
    private FandomResponseMapper fandomResponseMapper;

    public FandomController(FandomService fandomService, FandomMapper fandomMapper, TokenService tokenService, FandomResponseMapper fandomResponseMapper) {
        this.fandomService = fandomService;
        this.fandomMapper = fandomMapper;
        this.tokenService = tokenService;
        this.fandomResponseMapper = fandomResponseMapper;
    }

    @RequestMapping(value = "/api/v1/getFandom", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<FandomResponse> getFandom(@Valid @RequestBody Map<String, String> body, HttpServletResponse res) {
        Fandom fandom = fandomService.getFandom(Long.parseLong(body.get("oidFandom")));
        FandomResponse response = fandomResponseMapper.toFandomResponse(fandom);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/getFandoms", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Collection<Fandom> getAll() {
        return fandomService.getFandoms();
    }

    @RequestMapping(value = "/api/v1/getFandomByName", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<FandomResponse> getFandomByName(@Valid @RequestBody Map<String, Object> body, HttpServletResponse res) {
        Fandom request = fandomMapper.toFandom(body);
        Fandom fandom = fandomService.getFandomByName(request);
        FandomResponse response = fandomResponseMapper.toFandomResponse(fandom);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/v1/addFandom", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<FandomResponse> addFandom(@RequestHeader HttpHeaders headers, @Valid @RequestBody Map<String, Object> body) {
        tokenService.authenticate(headers.getFirst("jwt"), Long.valueOf((Integer) body.get("creator")));
        Fandom fandom = fandomMapper.toFandom(body);
        Fandom addedFandom = fandomService.addFandom(fandom);
        FandomResponse response = fandomResponseMapper.toFandomResponse(addedFandom);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
