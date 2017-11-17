package br.com.uniftec.ecommercemobile.model;

public class PedidoProdutoResponse {

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
