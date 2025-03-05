package br.github.ntidudu.Application.entity.Chamado;

import br.github.ntidudu.Application.entity.Usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "chamado")
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private PrioridadeChamado prioridade;

    @Enumerated(EnumType.STRING)
    private StatusChamado status;

    private LocalDate criacao;

    @ManyToOne(
        fetch = FetchType.LAZY
        )
    @JoinColumn(name  = "usuario_id")
    private Usuario usuario;


    public Chamado(Long id, String titulo, String descricao, PrioridadeChamado prioridade, StatusChamado status,
            LocalDate criacao, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = status;
        this.criacao = criacao;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Chamado chamado = (Chamado) o;
        return Objects.equals(id, chamado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public LocalDate getCriacao() {
        return criacao;
    }

    public void setCriacao(LocalDate criacao) {
        this.criacao = criacao;
    }
}
