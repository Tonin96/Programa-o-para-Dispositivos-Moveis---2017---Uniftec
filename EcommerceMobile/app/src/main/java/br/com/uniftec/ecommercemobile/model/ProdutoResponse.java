package br.com.uniftec.ecommercemobile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoResponse {

    private Long id;
    private String descricao;
    private String nome;
    private Double preco;
    private Double precoDesconto;
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
}
