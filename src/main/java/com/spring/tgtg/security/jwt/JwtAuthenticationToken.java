package com.spring.tgtg.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public JwtAuthenticationToken(String token) {
        super(null, token); // principal =null, credentials= token
    }
}
