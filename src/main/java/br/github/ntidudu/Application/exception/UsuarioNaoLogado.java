package br.github.ntidudu.Application.exception;

public class UsuarioNaoLogado extends RuntimeException {
    
    public UsuarioNaoLogado(String message){
        super(message);
    }

}
