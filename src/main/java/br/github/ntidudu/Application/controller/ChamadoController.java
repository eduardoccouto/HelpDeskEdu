package br.github.ntidudu.Application.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.github.ntidudu.Application.dto.ChamadoDTO;
import br.github.ntidudu.Application.dto.pesquisaChamadoDTO;
import br.github.ntidudu.Application.entity.Chamado.Chamado;
import br.github.ntidudu.Application.entity.Chamado.PrioridadeChamado;
import br.github.ntidudu.Application.entity.Chamado.StatusChamado;
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
    @PostMapping("v1")
    public ResponseEntity<Object> cadastrarChamado(@RequestBody @Valid ChamadoDTO chamadoDTO) {

        Chamado chamadoEntity = chamadoMapper.toEntity(chamadoDTO);

        chamadoService.cadastrarChamado(chamadoEntity);

        var url = gerarHeaderLocation(chamadoEntity.getId());

        return ResponseEntity.created(url).build();

    }

    @PreAuthorize("hasAutority('TECNICO')")
    @GetMapping("v1")
    public ResponseEntity<pesquisaChamadoDTO> filtrarChamadoPorID(@RequestParam @Valid Long id) {

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
    @PutMapping("v1/{id}")
    public ResponseEntity<Object> atualizarStatus(@PathVariable Long id,
            @RequestBody StatusChamado statutChamado) {

        chamadoService.atualizarStatusChamadoPorId(id, statutChamado);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('TECNICO')")
    @GetMapping("v2")
    public ResponseEntity<Page<pesquisaChamadoDTO>> pesquisa(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) PrioridadeChamado prioridadeChamado,
            @RequestParam(required = false) StatusChamado statusChamado,
            @RequestParam(required = false) String nome_usuario,
            @RequestParam(required = false, defaultValue = "0") Integer pagina,
            @RequestParam(required = false, defaultValue = "10") Integer tamanhoPagina) {

        var paginaResultado = chamadoService.pesquisa(id,
                        prioridadeChamado,
                        statusChamado,
                        titulo,
                        nome_usuario,
                        pagina,
                        tamanhoPagina)
                .map(chamadoMapper::toDTO);

        return ResponseEntity.ok(paginaResultado);
    }

}
