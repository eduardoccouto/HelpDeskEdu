package br.github.ntidudu.Application.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.github.ntidudu.Application.dto.UsuarioDTO;
import br.github.ntidudu.Application.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> cadastrarUsuarioBasico(@RequestBody UsuarioDTO entity) {
        var usuarioEntity = entity.mapearUsuarioBasico();
        usuarioService.cadastrarUsuario(usuarioEntity);

        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("{id}")
        .buildAndExpand(usuarioEntity.getId())
        .toUri();

        return ResponseEntity.created(location).build();
        
    }
    
}
