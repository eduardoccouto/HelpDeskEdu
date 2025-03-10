package br.github.ntidudu.Application.dto;

import java.util.Collection;

import br.github.ntidudu.Application.entity.Usuario.FuncaoUsuario;
import br.github.ntidudu.Application.entity.Usuario.Usuario;

public record UsuarioDTO(String nome, String username, String password, String email, Collection<FuncaoUsuario> funcao) {

    public Usuario mapearUsuarioBasico(){

        Usuario new_usuario = new Usuario();
        new_usuario.setUsername(username);
        new_usuario.setNome(nome);
        new_usuario.setEmail(email);
        new_usuario.setPassword(password);
        new_usuario.setFuncao(funcao);

        return new_usuario;
        
        
    }

}

