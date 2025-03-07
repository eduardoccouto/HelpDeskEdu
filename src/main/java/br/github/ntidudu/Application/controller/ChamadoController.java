package br.github.ntidudu.Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.github.ntidudu.Application.dto.ChamadoDtoResponse;
import br.github.ntidudu.Application.service.ChamadoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("chamados")
public class ChamadoController {
    
    @Autowired
    private ChamadoService chamadoService;


    

    @GetMapping("v1")
    public ResponseEntity<ChamadoDtoResponse> filtrarChamadoPorID(@RequestParam Long id) {

        var chamado = chamadoService.buscarChamadoPorId(id).map(x -> new ChamadoDtoResponse(x));

        return ResponseEntity.ok(chamado.get());
    }

   @DeleteMapping("v1/{id}")
   public ResponseEntity<Void> deletarChamadoPorId(@PathVariable Long id){

        chamadoService.excluirChamadoPorId(id);

        return ResponseEntity.ok().build();

   

   }
    

}
