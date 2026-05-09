package com.autobots.automanager.dto.requisicao;

import com.autobots.automanager.enumeracao.TipoVeiculo;
import lombok.Data;

@Data
public class VeiculoRequest {
    private TipoVeiculo tipo;
    private String modelo;
    private String placa;
    private Long proprietarioId;

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public Long getProprietarioId() {
        return proprietarioId;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setProprietarioId(Long proprietarioId) {
        this.proprietarioId = proprietarioId;
    }
}