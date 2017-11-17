package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;
import java.util.List;

public class Pedido implements Serializable {

    private String data;
    private String status;
    private Double total;
    private UsuarioEndereco enderecoEntrega;
    private List<PedidoProduto> itens;

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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public UsuarioEndereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(UsuarioEndereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<PedidoProduto> getItens() {
        return itens;
    }

    public void setItens(List<PedidoProduto> itens) {
        this.itens = itens;
    }
}
