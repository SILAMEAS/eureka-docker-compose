package com.laq.auth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth") // existing mapping
public class UserController {

    // change from "/auth/me" to "/me"
    @GetMapping("/me")
    public String getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        // your code here
        return jwt.getClaimAsString("sub");
    }

    // or add alias
    @GetMapping("/auth/me")
    public String getCurrentUserAlias(@AuthenticationPrincipal Jwt jwt) {
        return getCurrentUser(jwt);
    }
}