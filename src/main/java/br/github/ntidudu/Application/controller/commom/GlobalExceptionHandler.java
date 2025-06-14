package br.github.ntidudu.Application.controller.commom;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.github.ntidudu.Application.dto.ErroCampo;
import br.github.ntidudu.Application.dto.ErroResposta;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<ErroCampo> listaErros = e.getFieldErrors()
                .stream()
                .map(x -> new ErroCampo(x.getField(), x.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), 
        "Erro de validação", listaErros);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta	handleAllExceptions(Exception e){
        return  ErroResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErroResposta handleAuthorizationDeniedException(AuthorizationDeniedException e){
        return new ErroResposta(401,"Acesso negado", null);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleNoSuchElementException(NoSuchElementException e){
        return ErroResposta.respotaPadrao("Chamado não encontrado");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta handleUsernameNotFoundException(UsernameNotFoundException e){
        return ErroResposta.respotaPadrao("Credenciais inválidas");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleDataIntegrityViolationException(DataIntegrityViolationException e){

        return new ErroResposta(HttpStatus.CONFLICT.value(), "Erro de integridade nos dados" ,  null);
    }


    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleNullPointerException(NullPointerException e){
        return ErroResposta.respotaPadrao("Null data");
    }



}
