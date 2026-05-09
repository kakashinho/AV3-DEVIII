package com.autobots.automanager.entidade;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.autobots.automanager.enumeracao.TipoDocumento;

import lombok.Data;

@Data
@Entity
public class Documento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private TipoDocumento tipo;
	@Column(nullable = false)
	private Date dataEmissao;
	@Column(unique = true, nullable = false)
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

    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setId(Long id) {
        this.id = id;
    }
}