package com.autobots.automanager.dto.requisicao;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CredencialRequest {
    @NotNull
    private boolean inativo;
    private String nomeUsuario;
    private String senha;
    private Long codigo;

    public boolean isInativo() {
        return inativo;
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

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
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