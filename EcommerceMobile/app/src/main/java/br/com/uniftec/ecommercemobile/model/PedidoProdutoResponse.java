package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;

public class PedidoProdutoResponse implements Serializable {

    private Long id;
    private ProdutoResponse produto;
    private Double valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProdutoResponse getProduto() {
        return produto;
    }

    public void setProduto(ProdutoResponse produto) {
        this.produto = produto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
