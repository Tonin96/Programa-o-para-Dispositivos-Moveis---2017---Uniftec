package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Produto implements Serializable {

    private Long id;
    private String descricao;
    private String nome;
    private Double preco = 0.0;
    private Double precoDesconto = 0.0;
    private ProdutoCategoria categoria;
    private ProdutoImagem imagemPrincipal;
    private List<ProdutoImagem> imagens;

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

    public ProdutoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ProdutoCategoria categoria) {
        this.categoria = categoria;
    }

    public ProdutoImagem getImagemPrincipal() {
        return imagemPrincipal;
    }

    public void setImagemPrincipal(ProdutoImagem imagemPrincipal) {
        this.imagemPrincipal = imagemPrincipal;
    }

    public List<ProdutoImagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<ProdutoImagem> imagens) {
        this.imagens = imagens;
    }
}
