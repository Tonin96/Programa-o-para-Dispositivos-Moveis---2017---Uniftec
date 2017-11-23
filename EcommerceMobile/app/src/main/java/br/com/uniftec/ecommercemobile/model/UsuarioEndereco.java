package br.com.uniftec.ecommercemobile.model;

import java.io.Serializable;

public class UsuarioEndereco implements Serializable {

    private String bairro;
    private String cidade;
    private String complemento;
    private String estado;
    private String logradouro;
    private String numero;

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {

        return getLogradouro() + ", " + getNumero() + ", " + getBairro() + ", " + getComplemento();
    }
}
