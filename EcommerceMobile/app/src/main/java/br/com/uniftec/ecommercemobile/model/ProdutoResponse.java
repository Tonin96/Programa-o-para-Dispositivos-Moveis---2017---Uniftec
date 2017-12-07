package br.com.uniftec.ecommercemobile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoResponse {

    private Long id;
    private String descricao;
    private String nome;
    private Double preco = 0.0;
    private Double precoDesconto = 0.0;
    private ProdutoCategoriaResponse categoria;
    private ProdutoImagemResponse imagemPrincipal;
    private List<ProdutoImagemResponse> imagens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getPrecoDesconto() {
        return precoDesconto;
    }

    public void setPrecoDesconto(Double precoDesconto) {
        this.precoDesconto = precoDesconto;
    }

    public ProdutoCategoriaResponse getCategoria() {
        return categoria;
    }

    public void setCategoria(ProdutoCategoriaResponse categoria) {
        this.categoria = categoria;
    }

    public ProdutoImagemResponse getImagemPrincipal() {
        return imagemPrincipal;
    }

    public void setImagemPrincipal(ProdutoImagemResponse imagemPrincipal) {
        this.imagemPrincipal = imagemPrincipal;
    }

    public List<ProdutoImagemResponse> getImagens() {
        return imagens;
    }

    public void setImagens(List<ProdutoImagemResponse> imagens) {
        this.imagens = imagens;
    }

    public Produto getProduto() {
        Produto produto = new Produto();
        produto.setId(getId());
        produto.setDescricao(getDescricao());
        produto.setNome(getNome());
        produto.setPreco(getPreco());
        produto.setPrecoDesconto(getPrecoDesconto());
        ProdutoCategoria produtoCategoria = new ProdutoCategoria();
        produtoCategoria.setNome(getCategoria().getNome());
        produto.setCategoria(produtoCategoria);
        ProdutoImagem produtoImagem = new ProdutoImagem();
        produtoImagem.setUrl(getImagemPrincipal().getUrl());
        ArrayList<ProdutoImagem> produtoImagemArrayList = new ArrayList<ProdutoImagem>();
        for (ProdutoImagemResponse produtoImagemResponse : getImagens()) {
            produtoImagemArrayList.add(produtoImagemResponse.getProdutoImagem());
        }
        produto.setImagens(produtoImagemArrayList);
        produto.setImagemPrincipal(produtoImagem);

        return produto;
    }

}
