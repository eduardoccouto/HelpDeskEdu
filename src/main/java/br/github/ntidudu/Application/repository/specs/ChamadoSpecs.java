package br.github.ntidudu.Application.repository.specs;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import br.github.ntidudu.Application.entity.Chamado.Chamado;
import br.github.ntidudu.Application.entity.Chamado.PrioridadeChamado;
import br.github.ntidudu.Application.entity.Chamado.StatusChamado;
import br.github.ntidudu.Application.entity.Usuario.Usuario;

public class ChamadoSpecs {

    public static Specification<Chamado> idEqual(Long id){
        return (root, _, cb ) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Chamado> tituloLike(String titulo){
        return (root, _, cb) -> cb.like(cb.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }

    public static Specification<Chamado> prioridadeEqual(PrioridadeChamado prioridade){
        return (root, _, cb) -> cb.equal(root.get("prioridade"), prioridade);
    }

    public static Specification<Chamado> statusChamadoEqual(StatusChamado status){
        return (root, _, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<Chamado> nomeUsuarioLike(String nome){
        return (root, _, cb) -> {
            Join<Usuario, Chamado> joinUsuario = root.join("usuario", JoinType.INNER);
            return cb.like(cb.upper(joinUsuario.get("username")), "%" + nome  + "%");
        };

    }
    
}
