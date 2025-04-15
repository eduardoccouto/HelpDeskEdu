package br.github.ntidudu.Application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import br.github.ntidudu.Application.entity.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.github.ntidudu.Application.dto.pesquisaChamadoDTO;
import br.github.ntidudu.Application.entity.Chamado.Chamado;
import br.github.ntidudu.Application.entity.Chamado.PrioridadeChamado;
import br.github.ntidudu.Application.entity.Chamado.StatusChamado;
import br.github.ntidudu.Application.entity.Usuario.FuncaoUsuario;
import br.github.ntidudu.Application.exception.ChamadoNaoEncontrado;
import br.github.ntidudu.Application.exception.UsuarioNaoAutorizadoException;
import br.github.ntidudu.Application.exception.UsuarioNaoLogado;
import br.github.ntidudu.Application.mappers.ChamadoMapper;
import br.github.ntidudu.Application.repository.ChamadoRepository;
import br.github.ntidudu.Application.repository.specs.ChamadoSpecs;
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

        Usuario usuario_logado = sessionValidator.validarSessao();

        if (usuario_logado != null) {

            chamado.setStatus(StatusChamado.ABERTO);
            chamado.setCriacao(LocalDateTime.now());
            chamado.setUsuario(usuario_logado);
            return chamadoRepository.save(chamado);
        }

        throw new UsuarioNaoLogado("Nenhuma sessão aberta");

    }

    @Transactional
    public void excluirChamado(Chamado chamado) {

        chamadoRepository.delete(chamado);

    }

    public Optional<Chamado> buscarChamadoPorId(Long id) {
        return chamadoRepository.findById(id);
    }

    @Transactional
    public void atualizarStatusChamadoPorId(Long id, StatusChamado statusChamado) {

        Optional<Chamado> chamado = chamadoRepository.findById(id);

        if (chamado.isPresent()) {
            chamado.get().setStatus(statusChamado);
            chamadoRepository.save(chamado.get());
        }

        throw new ChamadoNaoEncontrado("Chamado não encontrado");

    }

    public List<pesquisaChamadoDTO> buscarChamadoPorPrioridade(PrioridadeChamado prioridadeChamado) {

        var sessao = sessionValidator.validarSessao();

        if (sessao.getFuncao().contains(FuncaoUsuario.TECNICO)) {
            return chamadoRepository.findAllByPrioridadeChamado(prioridadeChamado)
                    .stream()
                    .map(x -> chamadoMapper.toDTO(x))
                    .toList();
        }
        throw new UsuarioNaoAutorizadoException("Usuário não autorizado");

    }


    public Page<Chamado> pesquisa(
        Long id,
        PrioridadeChamado prioridadeChamado,
        StatusChamado statusChamado,
        String titulo,
        String nome_usuario,
        Integer pagina,
        Integer tamanhoPagina){
        
            Specification<Chamado> specs = Specification.where((_, _, cb) -> cb.conjunction());

            if(id != null){
                specs = specs.and(ChamadoSpecs.idEqual(id));
            }

            if(prioridadeChamado != null){
                specs = specs.and(ChamadoSpecs.prioridadeEqual(prioridadeChamado));
            }

            if(statusChamado != null){
                specs = specs.and(ChamadoSpecs.statusChamadoEqual(statusChamado));
            }

            if(titulo != null){
                specs = specs.and(ChamadoSpecs.tituloLike(titulo));
            }

            if(nome_usuario != null) {
                specs = specs.and(ChamadoSpecs.nomeUsuarioLike(nome_usuario));
            }

            Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

            return chamadoRepository.findAll(specs, pageRequest);


        
        
    }


    public List<Chamado> buscarChamadoPorTitulo(String titulo) {
        return chamadoRepository.findAllByTitulo(titulo);
    }
}
