package com.crm.mgr.rest;

import com.crm.mgr.service.impl.JwtService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jwt")
@CrossOrigin
public class JwtRestController {
    private final JwtService jwtService;

    public JwtRestController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping(path = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
    public String signIn(@RequestParam String login, @RequestParam String password) throws Exception {
        return jwtService.signIn(login, password);
    }
}
