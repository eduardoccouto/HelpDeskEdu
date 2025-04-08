package br.github.ntidudu.Application.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import br.github.ntidudu.Application.entity.Chamado.Chamado;
import br.github.ntidudu.Application.entity.Chamado.PrioridadeChamado;
import br.github.ntidudu.Application.entity.Chamado.StatusChamado;

public class ChamadoSpecs {

    public static Specification<Chamado> idEqual(Long id){
        return (root, _, cb ) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Chamado> tituloLike(String titulo){
        return (root, _, cb) -> cb.like(cb.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }

    public static Specification<Chamado> prioridadeEqual(PrioridadeChamado prioridadeChamado){
        return (root, _, cb) -> cb.equal(root.get("prioridadeChamado"), prioridadeChamado);
    }

    public static Specification<Chamado> statusChamadoEqual(StatusChamado statusChamado){
        return (root, _, cb) -> cb.equal(root.get("statusChamado"), statusChamado);
    }
    
}
