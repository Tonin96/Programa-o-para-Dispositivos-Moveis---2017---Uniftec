package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;

public class ProdutoImagemResponse implements Serializable {

    private Long id;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ProdutoImagem getProdutoImagem() {
        ProdutoImagem produtoImagem = new ProdutoImagem();
        produtoImagem.setUrl(getUrl());

        return produtoImagem;
    }
}
