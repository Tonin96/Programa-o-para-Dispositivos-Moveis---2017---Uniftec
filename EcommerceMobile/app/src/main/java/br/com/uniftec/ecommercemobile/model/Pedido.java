package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedido implements Serializable {

    public final static String STATUS_PEDIDO_ABERTO  = "ABERTO";
    public final static String STATUS_PEDIDO_FECHADO  = "FECHADO";

    private Date data;
    private Carrinho carrinho;
    private String status;

    public Date getData() {
        return data;
    }

    public String getDataFormatada(){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(data);
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
}
