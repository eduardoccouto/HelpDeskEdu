package br.github.ntidudu.Application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.github.ntidudu.Application.dto.ChamadoDTO;
import br.github.ntidudu.Application.entity.Chamado.Chamado;
import br.github.ntidudu.Application.entity.Chamado.PrioridadeChamado;
import br.github.ntidudu.Application.entity.Chamado.StatusChamado;
import br.github.ntidudu.Application.entity.Usuario.Usuario;
import br.github.ntidudu.Application.exception.UsuarioNaoLogado;
import br.github.ntidudu.Application.mappers.ChamadoMapper;
import br.github.ntidudu.Application.repository.ChamadoRepository;
import br.github.ntidudu.Application.security.UserAuthenticade;
import br.github.ntidudu.Application.validator.SessionValidator;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private SessionValidator sessionValidator;

    @Autowired
    private ChamadoMapper chamadoMapper;

    @Transactional
    public Chamado cadastrarChamado(Chamado chamado) {

        // Authentication authentication =
        // SecurityContextHolder.getContext().getAuthentication();

        // if (authentication != null && authentication.getPrincipal() instanceof
        // UserAuthenticade) {
        // UserAuthenticade userDetails = (UserAuthenticade)
        // authentication.getPrincipal();
        // Usuario usuario_logado = userDetails.getUsuario();

        // chamado.setUsuario(usuario_logado);
        // }

        var sessao = sessionValidator.validarSessao();

        if (sessao != null) {

            chamado.setStatus(StatusChamado.ABERTO);
            chamado.setCriacao(LocalDateTime.now());
            chamado.setUsuario(sessao);
            return chamadoRepository.save(chamado);
        }

        throw new UsuarioNaoLogado("Nenhuma sessão aberta");
        
        

    }

    public void excluirChamadoPorId(Long id) {
        var chamado = chamadoRepository.findById(id);

        if (chamado.isPresent()) {
            chamadoRepository.delete(chamado.get());
        }
    }

    public Optional<Chamado> buscarChamadoPorId(Long id) {
        return chamadoRepository.findById(id);
    }

    public void atualizarStatusChamadoPorId(Long id, StatusChamado statusChamado) {

        Optional<Chamado> chamado = chamadoRepository.findById(id);

        if (chamado.isPresent()) {
            chamado.get().setStatus(statusChamado);
            chamadoRepository.save(chamado.get());
        }

    }

    public List<ChamadoDTO> buscarChamadoPorPrioridade(PrioridadeChamado prioridadeChamado) {
        return chamadoRepository.findAllByPrioridadeChamado(prioridadeChamado)
                .stream()
                .map(x -> chamadoMapper.toDTO(x))
                .toList();
    }

    public List<Chamado> buscarChamadoPorTitulo(String titulo) {
        return chamadoRepository.findAllByTitulo(titulo);
    }
}
