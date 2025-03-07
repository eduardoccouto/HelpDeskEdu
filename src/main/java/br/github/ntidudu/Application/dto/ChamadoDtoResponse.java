package br.github.ntidudu.Application.dto;

import br.github.ntidudu.Application.entity.Chamado.Chamado;
import br.github.ntidudu.Application.entity.Chamado.PrioridadeChamado;
import br.github.ntidudu.Application.entity.Chamado.StatusChamado;

public class ChamadoDtoResponse {

    private String titulo;
    private String descricao;
    private PrioridadeChamado prioridade;
    private StatusChamado status;

    public ChamadoDtoResponse(Chamado chamado){
        this.titulo = chamado.getTitulo();
        this.descricao = chamado.getDescricao();
        this.prioridade = chamado.getPrioridade();
        this.status = chamado.getStatus();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PrioridadeChamado getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeChamado prioridade) {
        this.prioridade = prioridade;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

}
