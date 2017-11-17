package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;

/**
 * TODO: adicionar enderecoEntrega
 * TODO: adicionar itens
 * */
public class Pedido implements Serializable {

    private String data;
    private String status;
    private Double total;

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
}
