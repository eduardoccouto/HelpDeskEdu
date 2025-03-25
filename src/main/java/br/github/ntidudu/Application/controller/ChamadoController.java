package br.github.ntidudu.Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.github.ntidudu.Application.dto.ChamadoDTO;
import br.github.ntidudu.Application.entity.Chamado.Chamado;
import br.github.ntidudu.Application.entity.Chamado.StatusChamado;
import br.github.ntidudu.Application.exception.ChamadoNaoEncontrado;
import br.github.ntidudu.Application.mappers.ChamadoMapper;
import br.github.ntidudu.Application.service.ChamadoService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("chamados")
public class ChamadoController implements GenericController {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private ChamadoMapper chamadoMapper;

    @PreAuthorize("hasAuthority('USUARIO_BASICO')")
    @PostMapping
    public ResponseEntity<Object> cadastrarUsuarioBasico(@RequestBody @Valid ChamadoDTO chamadoDTO) {

        Chamado chamadoEntity = chamadoMapper.toEntity(chamadoDTO);

        chamadoService.cadastrarChamado(chamadoEntity);

        var url = gerarHeaderLocation(chamadoEntity.getId());

        return ResponseEntity.created(url).build();

    }

    @PreAuthorize("hasAutority('TECNICO')")
    @GetMapping("v1")
    public ResponseEntity<ChamadoDTO> filtrarChamadoPorID(@RequestParam @Valid Long id) {

        var chamado = chamadoService.buscarChamadoPorId(id)
                .map(chamadoMapper::toDTO);

        return ResponseEntity.ok(chamado.get());

    }

    @PreAuthorize("hasAutority('TECNICO')")
    @DeleteMapping("v1/{id}")
    public ResponseEntity<?> deletarChamadoPorId(@PathVariable Long id) {

        return chamadoService.buscarChamadoPorId(id)
                .map(chamado -> {
                    chamadoService.excluirChamado(chamado);
                    return ResponseEntity.noContent().build();

                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PreAuthorize("hasAutority('TECNICO')")
    @PutMapping("path/{id}")
    public ResponseEntity<Object> atualizarStatus(@PathVariable Long id,
            @RequestBody StatusChamado statutChamado) {

        chamadoService.atualizarStatusChamadoPorId(id, statutChamado);

        return ResponseEntity.ok().build();
    }

}
