package br.github.ntidudu.Application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.github.ntidudu.Application.dto.ChamadoDTO;
import br.github.ntidudu.Application.entity.Chamado.Chamado;

@Mapper(componentModel = "spring")
public interface ChamadoMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    Chamado toEntity(ChamadoDTO chamadoDTO);

    ChamadoDTO toDTO(Chamado chamado);

    

}
