package br.github.ntidudu.Application.dto;

import java.util.Collection;

import br.github.ntidudu.Application.entity.Usuario.FuncaoUsuario;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Usuario")
public record UsuarioDTO(Long id, String nome, String username, String password, String email, Collection<FuncaoUsuario> funcao) {

  

}

