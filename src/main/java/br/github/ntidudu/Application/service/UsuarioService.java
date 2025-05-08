package br.github.ntidudu.Application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.github.ntidudu.Application.entity.Usuario.Usuario;
import br.github.ntidudu.Application.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrarUsuario(Usuario usuario){

        var senha = usuario.getPassword();
        usuario.setPassword(passwordEncoder.encode(senha));

        return usuarioRepository.save(usuario);
    }

}
