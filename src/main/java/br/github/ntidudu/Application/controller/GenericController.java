package br.github.ntidudu.Application.controller;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public interface GenericController {
    default URI gerarHeaderLocation(Long id){
        return ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("{id}")
        .buildAndExpand(id)
        .toUri();
    };
}
