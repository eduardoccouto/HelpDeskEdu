package br.github.ntidudu.Application.controller.v1;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.github.ntidudu.Application.dto.UsuarioDTO;

import br.github.ntidudu.Application.entity.Usuario.Usuario;
import br.github.ntidudu.Application.mappers.UsuarioMapper;
import br.github.ntidudu.Application.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Usuário")
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Operation(summary = "Cadastro de usuários", description = "Cadastro feito a partir de login autorizado.")
    @PreAuthorize("hasAuthority('ADM')")
    @PostMapping("cadastro")
    public ResponseEntity<Void> cadastrarUsuarioBasico(@RequestBody UsuarioDTO entity) {
        Usuario newUser = usuarioMapper.toEntity(entity) ;
  
        usuarioService.cadastrarUsuario(newUser);

        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("{id}")
        .buildAndExpand(newUser.getId())
        .toUri();

        return ResponseEntity.created(location).build();
        
    }


    
}
