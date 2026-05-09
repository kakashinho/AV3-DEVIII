package com.autobots.automanager.dto.resposta;

import java.util.Date;

import lombok.Data;

@Data
public class CredencialResponse {
    private Long id;
    private Date criacao;
    private Date ultimoAcesso;
    private boolean inativo;
    private String tipo; 
    private String nomeUsuario;
    private String senha;
    private Long codigo;

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

    public String getTipo() {
        return tipo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
}
