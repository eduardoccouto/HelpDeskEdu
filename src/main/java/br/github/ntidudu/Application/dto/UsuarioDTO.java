package br.github.ntidudu.Application.dto;

import br.github.ntidudu.Application.entity.Usuario.FuncaoUsuario;
import br.github.ntidudu.Application.entity.Usuario.Usuario;

public record UsuarioDTO(String nome, String usuario_, String Senha, String email) {

    public Usuario mapearUsuarioBasico(){

        Usuario usuario = new Usuario();
        usuario.setUsuario(usuario_);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(Senha);
        usuario.setFuncao(FuncaoUsuario.USUARIO_BASICO);

        return usuario;
        
        
    }

}

