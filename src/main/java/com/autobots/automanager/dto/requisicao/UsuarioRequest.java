package com.autobots.automanager.dto.requisicao;

import lombok.Data;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;
import java.util.HashSet;
import com.autobots.automanager.enumeracao.PerfilUsuario;

@Data
public class UsuarioRequest {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    private String nomeSocial;
    
    @NotNull
    @Size(min = 1, message = "Pelo menos um perfil deve ser informado")
    private Set<PerfilUsuario> perfis = new HashSet<>();
    
    @Valid
    private EnderecoRequest endereco;
    
    @Valid
    private Set<TelefoneRequest> telefones = new HashSet<>();
    
    @Valid
    private Set<EmailRequest> emails = new HashSet<>();
    
    @Valid
    private Set<DocumentoRequest> documentos = new HashSet<>();
    
    @Valid
    private Set<CredencialRequest> credenciais = new HashSet<>();

    public String getNome() {
        return nome;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public Set<PerfilUsuario> getPerfis() {
        return perfis;
    }

    public EnderecoRequest getEndereco() {
        return endereco;
    }

    public Set<TelefoneRequest> getTelefones() {
        return telefones;
    }

    public Set<EmailRequest> getEmails() {
        return emails;
    }

    public Set<DocumentoRequest> getDocumentos() {
        return documentos;
    }

    public Set<CredencialRequest> getCredenciais() {
        return credenciais;
    }
}