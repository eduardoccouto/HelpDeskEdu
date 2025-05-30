package br.github.ntidudu.Application.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.github.ntidudu.Application.entity.Chamado.PrioridadeChamado;
import br.github.ntidudu.Application.entity.Chamado.StatusChamado;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "Chamado")
public record ChamadoDTO(

                @NotBlank @Size(min = 5, max = 200, message = "Campo fora do padrão") String titulo,

                @NotBlank(message = "campo obrigatorio") String descricao,

                @NotNull(message = "campo vazio!") PrioridadeChamado prioridade,

                StatusChamado status,
                LocalDateTime criacao,
                LocalDateTime atualizacao

) implements Serializable {

}
