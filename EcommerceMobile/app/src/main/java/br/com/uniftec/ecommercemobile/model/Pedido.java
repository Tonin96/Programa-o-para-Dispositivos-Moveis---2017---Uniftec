package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Serializable {

    private Long id;
    private String cartaoCredito;
    private String codigoSeguranca;
    private Long idEnderecoUsuario;
    private ArrayList<Long> produtos;
    private String validadeCartaoCredito;

    private String data;
    private String status;
    private UsuarioEnderecoResponse enderecoEntrega;
    private Double total;
    private List<PedidoProdutoResponse> itensDoPedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCartaoCredito() { return cartaoCredito; }

    public void setCartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UsuarioEnderecoResponse getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(UsuarioEnderecoResponse enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<PedidoProdutoResponse> getItensDoPedido() {
        return itensDoPedido;
    }

    public void setItensDoPedido(List<PedidoProdutoResponse> itensDoPedido) {
        this.itensDoPedido = itensDoPedido;
    }
}
