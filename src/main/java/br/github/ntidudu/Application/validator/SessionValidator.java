package br.github.ntidudu.Application.validator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.github.ntidudu.Application.entity.Usuario.Usuario;
import br.github.ntidudu.Application.security.UserAuthenticade;

@Component
public class SessionValidator {
    
    public Usuario validarSessao(){

         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


         if (authentication != null && authentication.getPrincipal() instanceof UserAuthenticade) {
            UserAuthenticade userDetails = (UserAuthenticade) authentication.getPrincipal();
            Usuario usuario_logado = userDetails.getUsuario();

            return usuario_logado;
        }
        
        return null;

    }

}
