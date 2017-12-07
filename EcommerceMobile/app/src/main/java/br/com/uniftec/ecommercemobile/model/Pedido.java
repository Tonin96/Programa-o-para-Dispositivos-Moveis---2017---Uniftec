package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pedido implements Serializable {

    private String cartaoCredito;
    private String codigoSeguranca;
    private Long idEnderecoUsuario;
    private ArrayList<Long> produtos;
    private String validadeCartaoCredito;

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public Long getIdEnderecoUsuario() {
        return idEnderecoUsuario;
    }

    public void setIdEnderecoUsuario(Long idEnderecoUsuario) {
        this.idEnderecoUsuario = idEnderecoUsuario;
    }

    public ArrayList<Long> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Long> produtos) {
        this.produtos = produtos;
    }

    public String getValidadeCartaoCredito() {
        return validadeCartaoCredito;
    }

    public void setValidadeCartaoCredito(String validadeCartaoCredito) {
        this.validadeCartaoCredito = validadeCartaoCredito;
    }

    public String getCartaoCredito() {

        return cartaoCredito;
    }

    public void setCartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }
}
