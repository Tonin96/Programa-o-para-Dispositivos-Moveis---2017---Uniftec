package br.com.uniftec.ecommercemobile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoResponse {

    private Long id;
    private String data;
    private String status;
    private Double total;
    private List<UsuarioEnderecoResponse> enderecoEntrega;
    private List<PedidoProdutoResponse> itensDoPedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<UsuarioEnderecoResponse> getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(List<UsuarioEnderecoResponse> enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<PedidoProdutoResponse> getItensDoPedido() {
        return itensDoPedido;
    }

    public void setItensDoPedido(List<PedidoProdutoResponse> itensDoPedido) {
        this.itensDoPedido = itensDoPedido;
    }
}
