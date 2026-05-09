package com.autobots.automanager.entidade;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date cadastro;
    private String identificacao;

    @ManyToOne
    private Usuario cliente;

    @ManyToOne
    private Usuario funcionario;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
        name = "venda_mercadoria",
        joinColumns = @JoinColumn(name = "venda_id"),
        inverseJoinColumns = @JoinColumn(name = "mercadoria_id")
    )
    private Set<Mercadoria> mercadorias = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
        name = "venda_servico",
        joinColumns = @JoinColumn(name = "venda_id"),
        inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private Set<Servico> servicos = new HashSet<>();

    @ManyToOne
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Empresa empresa;

    public Long getId() {
        return id;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public Set<Mercadoria> getMercadorias() {
        return mercadorias;
    }

    public Set<Servico> getServicos() {
        return servicos;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public void setFuncionario(Usuario funcionario) {
        this.funcionario = funcionario;
    }

    public void setMercadorias(Set<Mercadoria> mercadorias) {
        this.mercadorias = mercadorias;
    }

    public void setServicos(Set<Servico> servicos) {
        this.servicos = servicos;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}