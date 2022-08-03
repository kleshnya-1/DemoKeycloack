package com.example.startkeycloak;

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/anonymous")
    public String getAnonymousInfo() {
        return "Anonymous";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String getUserInfo() {
        return "user info";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminInfo() {
        return "admin info";
    }

    @GetMapping("/service")
    @PreAuthorize("hasRole('SERVICE')")
    public String getServiceInfo() {
        return "service info";
    }

    @GetMapping("/me")
    public Object getMe() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SimpleKeycloakAccount account = (SimpleKeycloakAccount) authentication.getDetails();
        String tokenId = account.getKeycloakSecurityContext().getIdTokenString();
        String tokenAccess = account.getKeycloakSecurityContext().getTokenString();
        String tokenRefresh = account.getKeycloakSecurityContext().getRefreshToken();
        return authentication.getName() + " " + LocalDateTime.now();
    }
}
