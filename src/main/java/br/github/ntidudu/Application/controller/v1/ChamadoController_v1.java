package br.github.ntidudu.Application.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.github.ntidudu.Application.dto.ChamadoDTO;
import br.github.ntidudu.Application.dto.pesquisaChamadoDTO;
import br.github.ntidudu.Application.entity.Chamado.Chamado;
import br.github.ntidudu.Application.entity.Chamado.StatusChamado;
import br.github.ntidudu.Application.mappers.ChamadoMapper;
import br.github.ntidudu.Application.service.ChamadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@EnableMethodSecurity
@RequestMapping("api/v1/chamados")
@Tag(name = "Chamados - v1", description = "Operações da versão 1")
public class ChamadoController_v1 implements GenericController {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private ChamadoMapper chamadoMapper;

    @Operation(summary = "Cadastrar", description = "Cadastra novos chamados no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Erro de validação")
    })
    @PreAuthorize("hasAnyAuthority('USUARIO_BASICO', 'ADM', 'TECNICO')")
    @PostMapping
    public ResponseEntity<Object> cadastrarChamado(@RequestBody @Valid ChamadoDTO chamadoDTO) {

        Chamado chamadoEntity = chamadoMapper.toEntity(chamadoDTO);

        chamadoService.cadastrarChamado(chamadoEntity);

        var url = gerarHeaderLocation(chamadoEntity.getId());

        return ResponseEntity.created(url).build();

    }

    @Deprecated
    @PreAuthorize("hasAuthority('TECNICO')")
    @GetMapping
    public ResponseEntity<pesquisaChamadoDTO> filtrarChamadoPorID(@RequestParam @Valid Long id) {

        var chamado = chamadoService.buscarChamadoPorId(id)
                .map(chamadoMapper::toDTO);

        return ResponseEntity.ok(chamado.get());

    }

    @Operation(summary = "Deletar chamado", description = "Deleta chamados no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Chamado não encontrado")
    })
    @PreAuthorize("hasAnyAuthority('TECNICO', 'ADM')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarChamadoPorId(@PathVariable Long id) {

        return chamadoService.buscarChamadoPorId(id)
                .map(chamado -> {
                    chamadoService.excluirChamado(chamado);
                    return ResponseEntity.noContent().build();

                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Atualizar", description = "Atualiza chamados no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Erro de validação")
    })
    @PreAuthorize("hasAnyAuthority('TECNICO', 'ADM')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarStatus(@PathVariable Long id,
            @RequestBody StatusChamado statutChamado) {

        chamadoService.atualizarStatusChamadoPorId(id, statutChamado);

        return ResponseEntity.ok().build();
    }

    
    @Operation(summary = "Listar", description = "Lista os chamados abertos pelo usuário logado.")
    @ApiResponses({
        @ApiResponse(responseCode = "200")
    })
    @Cacheable("meus-chamados")
    @GetMapping("/meus-chamados")
    public ResponseEntity<Object> getMeusChamados(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {

        var chamados = chamadoService.meusChamdos(page, size).map(chamadoMapper::toDTO);

        return ResponseEntity.ok(chamados);

    }

}
