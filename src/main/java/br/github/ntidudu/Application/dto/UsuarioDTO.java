package br.github.ntidudu.Application.dto;

import br.github.ntidudu.Application.entity.Usuario.FuncaoUsuario;
import br.github.ntidudu.Application.entity.Usuario.Usuario;

public record UsuarioDTO(String nome, String usuario, String senha, String email, FuncaoUsuario funcao) {

    public Usuario mapearUsuarioBasico(){

        Usuario new_usuario = new Usuario();
        new_usuario.setUsuario(usuario);
        new_usuario.setNome(nome);
        new_usuario.setEmail(email);
        new_usuario.setSenha(senha);
        new_usuario.setFuncao(funcao);

        return new_usuario;
        
        
    }

}

