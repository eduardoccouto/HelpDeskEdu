package br.github.ntidudu.Application.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

public record ErroResposta(int status, String message, List<ErroCampo> errors) {

    public static ErroResposta respotaPadrao(String mensagem){
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
    }

    public static ErroResposta conflito(String mensagem){
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }

} 