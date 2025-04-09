package br.github.ntidudu.Application.dto;

import java.time.LocalDateTime;

import br.github.ntidudu.Application.entity.Chamado.PrioridadeChamado;
import br.github.ntidudu.Application.entity.Chamado.StatusChamado;

public record pesquisaChamadoDTO(

        Long id,
        String titulo,
        PrioridadeChamado prioridade,
        StatusChamado status,
        LocalDateTime criacao,
        LocalDateTime atualizacao,
        usuarioResponseDTO usuario,
        String descricao

) {

}
