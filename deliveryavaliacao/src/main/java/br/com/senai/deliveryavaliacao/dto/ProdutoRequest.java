package br.com.senai.deliveryavaliacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProdutoRequest {

    @NotBlank(message = "Informe o nome do produto")
    private String nome;

    @NotNull(message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "Informe o restaurante")
    private Integer restauranteId;

    public ProdutoRequest(String nome, BigDecimal preco, Integer restauranteId) {
        this.nome = nome;
        this.preco = preco;
        this.restauranteId = restauranteId;
    }

    public ProdutoRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Integer restauranteId) {
        this.restauranteId = restauranteId;
    }
}