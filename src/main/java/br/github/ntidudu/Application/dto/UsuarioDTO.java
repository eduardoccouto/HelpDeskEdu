package br.github.ntidudu.Application.dto;

import java.util.Collection;

import br.github.ntidudu.Application.entity.Usuario.FuncaoUsuario;

public record UsuarioDTO(Long id, String nome, String username, String password, String email, Collection<FuncaoUsuario> funcao) {

  

}

