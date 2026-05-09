package com.autobots.automanager.dto.resposta;

import java.util.Date;

import lombok.Data;

@Data
public class MercadoriaResponse {
    private Long id;
    private String nome;
    private String descricao;
    private double valor;
    private long quantidadeEstoque;
    private boolean disponivel;
    private Date validade;
    private Date fabricao;
    private Date cadastro;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public long getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public Date getValidade() {
        return validade;
    }

    public Date getFabricao() {
        return fabricao;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setQuantidadeEstoque(long quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public void setFabricao(Date fabricao) {
        this.fabricao = fabricao;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }
}