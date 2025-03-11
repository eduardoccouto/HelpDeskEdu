package br.github.ntidudu.Application.dto;

import java.time.LocalDateTime;

//import br.github.ntidudu.Application.entity.Chamado.Chamado;
import br.github.ntidudu.Application.entity.Chamado.PrioridadeChamado;
import br.github.ntidudu.Application.entity.Chamado.StatusChamado;

public record ChamadoDTO(
         
        String titulo,
        String descricao,
        PrioridadeChamado prioridade,
        StatusChamado status,
        LocalDateTime criacao,
        LocalDateTime atualizacao
        

) {

        // public Chamado chamadoMapping() {
        //     Chamado new_chamado = new Chamado();
        //     new_chamado.setTitulo(titulo);
        //     new_chamado.setDescricao(descricao);
        //     new_chamado.setPrioridade(prioridade);
        //     new_chamado.setStatus(StatusChamado.ABERTO);

        //     return new_chamado;
        // }

}
