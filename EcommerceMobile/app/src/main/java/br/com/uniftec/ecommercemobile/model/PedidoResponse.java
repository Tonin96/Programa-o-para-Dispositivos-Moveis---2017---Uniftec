package br.com.uniftec.ecommercemobile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoResponse {

    private Long id;
    private String data;
    private String status;
    private UsuarioEnderecoResponse enderecoEntrega;
    private Double total;
    private List<PedidoProdutoResponse> itens;

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

    public List<PedidoProdutoResponse> getItens() {
        return itens;
    }

    public void setItens(List<PedidoProdutoResponse> itens) {
        this.itens = itens;
    }

    public Pedido getPedido() {

        Pedido pedido = new Pedido();

        pedido.setId(getId());
        pedido.setData(getData());
        pedido.setStatus(getStatus());
        pedido.setEnderecoEntrega(getEnderecoEntrega());
        pedido.setTotal(getTotal());
        pedido.setItensDoPedido(getItens());

        return pedido;
    }
}
