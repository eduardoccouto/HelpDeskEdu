package br.github.ntidudu.Application.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.github.ntidudu.Application.dto.UsuarioDTO;
import br.github.ntidudu.Application.entity.Usuario.FuncaoUsuario;
import br.github.ntidudu.Application.entity.Usuario.Usuario;
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
        Usuario usuarioEntity = entity.mapearUsuarioBasico();
        if(usuarioEntity.getSenha() == null) {
            return ResponseEntity.status(403).build();
        }
        if(usuarioEntity.getFuncao() == FuncaoUsuario.TECNICO){
            return ResponseEntity.status(403).build();
        }
        usuarioService.cadastrarUsuario(usuarioEntity);

        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("{id}")
        .buildAndExpand(usuarioEntity.getId())
        .toUri();

        return ResponseEntity.created(location).build();
        
    }


    
}
