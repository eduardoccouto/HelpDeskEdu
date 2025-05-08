package br.github.ntidudu.Application.service;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.github.ntidudu.Application.security.JwtService;

@Service
public class AuthenticationService {

    private JwtService jwtService;

    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(Authentication authentication){
        return jwtService.generateToken(authentication);
    }
}
