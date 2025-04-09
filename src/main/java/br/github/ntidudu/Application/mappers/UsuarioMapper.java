package br.github.ntidudu.Application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.github.ntidudu.Application.dto.UsuarioDTO;
import br.github.ntidudu.Application.dto.usuarioResponseDTO;
import br.github.ntidudu.Application.entity.Usuario.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "chamados", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO);

    usuarioResponseDTO toDTO(Usuario usuario);
    
}