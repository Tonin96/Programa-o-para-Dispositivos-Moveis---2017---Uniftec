package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;

public class PedidoProduto implements Serializable {

    private Produto produto;
    private Double valor;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
