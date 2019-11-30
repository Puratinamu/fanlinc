package ca.utoronto.utm.mcs.projectcloudinfantry.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "forward:/";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
