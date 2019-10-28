package ca.utoronto.utm.mcs.projectcloudinfantry.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class RESTController {

    @RequestMapping(value = "/api/v1/health", method = GET)
    public ResponseEntity health() {
        return new ResponseEntity(HttpStatus.OK);
    }

}
