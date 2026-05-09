package com.autobots.automanager.entidade;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Credencial {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Date criacao;
	@Column()
	private Date ultimoAcesso;
	@Column(nullable = false)
	private boolean inativo;

    public Long getId() {
        return id;
    }

    public Date getCriacao() {
        return criacao;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public boolean isInativo() {
        return inativo;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }
}