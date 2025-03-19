package br.github.ntidudu.Application.exception;

public class UsuarioNaoAutorizadoException extends RuntimeException {

    public UsuarioNaoAutorizadoException(String message){
        super(message);
    }
}
