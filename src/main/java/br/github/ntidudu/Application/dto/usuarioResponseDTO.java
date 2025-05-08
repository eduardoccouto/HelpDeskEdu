package br.github.ntidudu.Application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Detalhes de usuário")
public record usuarioResponseDTO(String nome, String username, String email) {
    
}
