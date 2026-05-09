package com.autobots.automanager.entidade;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Mercadoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Usuario usuario;

	@Column(nullable = false)
	private Date validade;

	@Column(nullable = false)
	private Date fabricacao;

	@Column(nullable = false)
	private Date cadastro;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private double valor;

    @Column(nullable = false)
    private long quantidade;

	@Column
	private String descricao;

    private boolean disponivel;

    public Long getId() {
        return id;
    }

    public Date getValidade() {
        return validade;
    }

    public Date getFabricacao() {
        return fabricacao;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public void setFabricacao(Date fabricacao) {
        this.fabricacao = fabricacao;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

}