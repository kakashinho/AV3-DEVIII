package com.autobots.automanager.dto.requisicao;

import java.util.Date;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import lombok.Data;

@Data
public class MercadoriaRequest {
    @NotBlank
    private String nome;
    private String descricao;
    @Positive(message = "Valor deve ser maior que zero")
    private double valor;
    @NotNull
    @FutureOrPresent
    private Date validade;
    @NotNull
    @PastOrPresent
    private Date fabricao;
    @NotNull
    private long quantidade;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public Date getValidade() {
        return validade;
    }

    public Date getFabricao() {
        return fabricao;
    }

    public long getQuantidade() {
        return quantidade;
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

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public void setFabricao(Date fabricao) {
        this.fabricao = fabricao;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }
}
