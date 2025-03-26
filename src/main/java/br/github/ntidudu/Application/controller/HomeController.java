package br.github.ntidudu.Application.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
    
    @GetMapping("/")
    public String homePage(Authentication authentication) {
        return "Usuário logado " + authentication.getName();
    }
    
}
