package br.github.ntidudu.Application.repository;

import br.github.ntidudu.Application.entity.Chamado.Chamado;
import br.github.ntidudu.Application.entity.Chamado.PrioridadeChamado;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChamadoRepository extends JpaRepository<Chamado, Long>, JpaSpecificationExecutor<Chamado> {

    @Query(
        """
        SELECT c from Chamado c where c.prioridade =:prioridadeChamado
        """
    )
    List<Chamado> findAllByPrioridadeChamado(PrioridadeChamado prioridadeChamado);

    @Query("""
            SELECT c from Chamado c where c.titulo like :titulo
            """)
    List<Chamado> findAllByTitulo(@Param("titulo") String titulo);

    
}
