package br.com.uniftec.ecommercemobile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioEnderecoResponse implements Serializable{

    private Long id;
    private String bairro = "";
    private String cidade = "";
    private String complemento ="";
    private String estado = "";
    private String logradouro = "";
    private String numero = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public UsuarioEndereco getEndereco() {
        UsuarioEndereco endereco = new UsuarioEndereco();
        endereco.setBairro(getBairro());
        endereco.setCidade(getCidade());
        endereco.setComplemento(getComplemento());
        endereco.setEstado(getEstado());
        endereco.setLogradouro(getLogradouro());
        endereco.setNumero(getNumero());
        endereco.setId(getId());

        return endereco;
    }
}
