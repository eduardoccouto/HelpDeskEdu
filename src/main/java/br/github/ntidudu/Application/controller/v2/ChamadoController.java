package br.github.ntidudu.Application.controller.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.github.ntidudu.Application.dto.pesquisaChamadoDTO;
import br.github.ntidudu.Application.entity.Chamado.PrioridadeChamado;
import br.github.ntidudu.Application.entity.Chamado.StatusChamado;
import br.github.ntidudu.Application.mappers.ChamadoMapper;
import br.github.ntidudu.Application.service.ChamadoService;
import io.swagger.v3.oas.annotations.tags.Tag;


@EnableMethodSecurity
@RestController
@RequestMapping("api/v2/chamados")
@Tag(name = "Chamados - v2", description = "Operação da versão 2")
public class ChamadoController {
    
    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    ChamadoMapper chamadoMapper;

   @PreAuthorize("hasAuthority('ROLE_ADM')")
    @GetMapping
    public ResponseEntity<Page<pesquisaChamadoDTO>> pesquisa(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) PrioridadeChamado prioridade,
            @RequestParam(required = false) StatusChamado status,
            @RequestParam(required = false) String nome_usuario,
            @RequestParam(required = false, defaultValue = "0") Integer pagina,
            @RequestParam(required = false, defaultValue = "10") Integer tamanhoPagina) {

        var paginaResultado = chamadoService.pesquisa(id,
                prioridade,
                status,
                titulo,
                nome_usuario,
                pagina,
                tamanhoPagina)
                .map(chamadoMapper::toDTO);

        return ResponseEntity.ok(paginaResultado);
    }

}
