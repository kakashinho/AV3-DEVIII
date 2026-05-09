package com.autobots.automanager.dto.resposta;

import java.util.Date;

import com.autobots.automanager.enumeracao.TipoDocumento;

import lombok.Data;

@Data
public class DocumentoResponse {
    private Long id;
    private TipoDocumento tipo;
    private Date dataEmissao;
    private String numero;

    public Long getId() {
        return id;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public String getNumero() {
        return numero;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
