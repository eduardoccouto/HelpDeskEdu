package br.github.ntidudu.Application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.github.ntidudu.Application.dto.ChamadoDtoResponse;
import br.github.ntidudu.Application.entity.Chamado.Chamado;
import br.github.ntidudu.Application.entity.Chamado.PrioridadeChamado;
import br.github.ntidudu.Application.entity.Chamado.StatusChamado;
import br.github.ntidudu.Application.entity.Usuario.Usuario;
import br.github.ntidudu.Application.repository.ChamadoRepository;
import br.github.ntidudu.Application.security.UserAuthenticade;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado cadastrarChamado(Chamado chamado) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserAuthenticade) {
            UserAuthenticade userDetails = (UserAuthenticade) authentication.getPrincipal();
            Usuario usuario_logado = userDetails.getUsuario();

            chamado.setUsuario(usuario_logado);
        }

        chamado.setCriacao(LocalDateTime.now());

        return chamadoRepository.save(chamado);
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

    public List<ChamadoDtoResponse> buscarChamadoPorPrioridade(PrioridadeChamado prioridadeChamado) {
        return chamadoRepository
                .findAllByPrioridadeChamado(prioridadeChamado)
                .stream()
                .map(x -> new ChamadoDtoResponse(x))
                .toList();
    }

    public List<Chamado> buscarChamadoPorTitulo(String titulo) {
        return chamadoRepository.findAllByTitulo(titulo);
    }
}
