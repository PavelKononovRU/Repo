package com.example.core.common;



import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Map;

@Component
public class UserInfo {

    public String getExtID(Principal principal) {
        JwtAuthenticationToken kp = (JwtAuthenticationToken) principal;
        Jwt token = kp.getToken();
        Map<String, Object> userInfo = token.getClaims();
        return userInfo.get("sub").toString();
    }
}
