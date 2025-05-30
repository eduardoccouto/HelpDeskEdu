package br.github.ntidudu.Application.dto;

import jakarta.validation.constraints.NotNull;

public record loginDTO(
    @NotNull(message = "Campo vazio!")
    String username,
    @NotNull(message = "Campo vazio!") 
    String password) {
    
}
