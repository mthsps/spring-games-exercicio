package com.generation.games.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name="produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private double preco;

	@NotNull
	private int quantidade;

	@CreatedDate
    @Column(name = "adicionado_em", updatable = false)
    private Instant adicionadoEm = Instant.now();

    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private Categoria categoria;

	Produto() {};

	Produto(int quantidade) {
		this.quantidade = quantidade;
	};
    
	public Produto(String nome, double preco, int quantidade, Categoria categoria) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Instant getAdicionadoEm() {
		return adicionadoEm;
	}

	public void setAdicionadoEm(Instant adicionadoEm) {
		this.adicionadoEm = adicionadoEm;
	}

	public Categoria getCategoria() {
			return categoria;
		}

	public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}

}
