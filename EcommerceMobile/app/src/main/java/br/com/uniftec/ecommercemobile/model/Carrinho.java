package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bruno on 07/11/17.
 */

public class Carrinho implements Serializable {
    private Conta conta = new Conta();
    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }



}
