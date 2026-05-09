package com.autobots.automanager.dto.requisicao;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.autobots.automanager.enumeracao.TipoDocumento;

import lombok.Data;

@Data
public class DocumentoRequest {
    @NotNull
    private TipoDocumento tipo;
    @NotBlank
    private String numero;
    @NotNull
    private Date dataEmissao;

    public TipoDocumento getTipo() {
        return tipo;
    }

    public String getNumero() {
        return numero;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
}