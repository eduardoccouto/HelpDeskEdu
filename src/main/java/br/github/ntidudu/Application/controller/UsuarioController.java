package br.github.ntidudu.Application.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.github.ntidudu.Application.dto.UsuarioDTO;

import br.github.ntidudu.Application.entity.Usuario.Usuario;
import br.github.ntidudu.Application.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("hasAuthority('ADM')")
    @PostMapping("cadastro")
    public ResponseEntity<Void> cadastrarUsuarioBasico(@RequestBody UsuarioDTO entity) {
        Usuario usuarioEntity = entity.mapearUsuarioBasico();
  
        usuarioService.cadastrarUsuario(usuarioEntity);

        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("{id}")
        .buildAndExpand(usuarioEntity.getId())
        .toUri();

        return ResponseEntity.created(location).build();
        
    }


    
}
