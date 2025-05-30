package br.github.ntidudu.Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.github.ntidudu.Application.validator.SessionValidator;

@EnableMethodSecurity
@RestController
@RequestMapping("/helloworld")
public class helloworld {

    @Autowired
    private SessionValidator sessionValidator;

    @GetMapping
    public ResponseEntity<String> helloWorld() {

        var session = sessionValidator.getUserSession();

        return ResponseEntity.ok().body("Hello World " + session.getNome()
                + "\nFUNÇÃO: " + session.getFuncao());
    }

}
