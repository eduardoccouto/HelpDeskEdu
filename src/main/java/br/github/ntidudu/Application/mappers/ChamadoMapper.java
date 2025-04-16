package br.github.ntidudu.Application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import br.github.ntidudu.Application.dto.ChamadoDTO;
import br.github.ntidudu.Application.dto.pesquisaChamadoDTO;
import br.github.ntidudu.Application.entity.Chamado.Chamado;
import br.github.ntidudu.Application.repository.UsuarioRepository;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public abstract class ChamadoMapper {


    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    public abstract Chamado toEntity(ChamadoDTO chamadoDTO);

    
    public abstract pesquisaChamadoDTO toDTO(Chamado chamado);

    

}
