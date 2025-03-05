package br.github.ntidudu.Application.repository;

import br.github.ntidudu.Application.entity.Chamado.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
