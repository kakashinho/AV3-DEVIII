package com.autobots.automanager.dto.requisicao;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TelefoneRequest {
    @NotBlank private String ddd;
    @NotBlank private String numero;

    public String getDdd() {
        return ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}