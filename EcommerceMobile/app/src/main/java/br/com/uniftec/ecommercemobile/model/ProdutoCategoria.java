package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;

public class ProdutoCategoria implements Serializable {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
