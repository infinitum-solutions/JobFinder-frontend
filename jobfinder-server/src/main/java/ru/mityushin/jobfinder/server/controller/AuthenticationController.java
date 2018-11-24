package ru.mityushin.jobfinder.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mityushin.jobfinder.server.service.security.SecurityService;

import java.util.Map;

@CrossOrigin(value = "http://localhost:4200")
@RestController
public class AuthenticationController {

    private SecurityService securityService;

    public AuthenticationController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<HttpStatus> login(@RequestBody Map<String, String> body) {

        String username = body.getOrDefault("username", null);
        String password = body.getOrDefault("password", null);

        securityService.autoLogin(username, password);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
