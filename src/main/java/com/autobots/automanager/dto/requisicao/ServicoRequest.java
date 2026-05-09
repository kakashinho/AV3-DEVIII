package com.autobots.automanager.dto.requisicao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import com.autobots.automanager.enumeracao.StatusServico;

import lombok.Data;

@Data
public class ServicoRequest {
    @NotBlank
    private String nome;
    private String descricao;
    @Positive(message = "Valor deve ser maior que zero")
    private double valor;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
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

}