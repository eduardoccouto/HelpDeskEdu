package br.github.ntidudu.Application.mappers;

import org.mapstruct.Mapper;

import br.github.ntidudu.Application.dto.ChamadoDTO;
import br.github.ntidudu.Application.entity.Chamado.Chamado;

@Mapper(componentModel = "spring")
public interface ChamadoMapper {
    
    Chamado toEntity(ChamadoDTO chamadoDTO);

    ChamadoDTO toDTO(Chamado chamado);

    

}
