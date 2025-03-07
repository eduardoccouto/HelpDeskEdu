package br.github.ntidudu.Application.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.github.ntidudu.Application.dto.ChamadoDtoResponse;
import br.github.ntidudu.Application.entity.Chamado.Chamado;
import br.github.ntidudu.Application.entity.Chamado.PrioridadeChamado;
import br.github.ntidudu.Application.repository.ChamadoRepository;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    

    public Chamado cadastrarChamado(Chamado chamado) {

        return chamadoRepository.save(chamado);
    }

    public void excluirChamadoPorId(Long id){
        var chamado  = chamadoRepository.findById(id);

        if (chamado.isPresent()){
             chamadoRepository.delete(chamado.get());
        }
    }

    public Optional<Chamado> buscarChamadoPorId(Long id){
        return chamadoRepository.findById(id);
    }

    public List<ChamadoDtoResponse> buscarChamadoPorPrioridade(PrioridadeChamado prioridadeChamado){
        return chamadoRepository
        .findAllByPrioridadeChamado(prioridadeChamado)
        .stream()
        .map(x -> new ChamadoDtoResponse(x))
        .toList();
    }

    public List<Chamado> buscarChamadoPorTitulo(String titulo){
        return chamadoRepository.findAllByTitulo(titulo);
    }
}
