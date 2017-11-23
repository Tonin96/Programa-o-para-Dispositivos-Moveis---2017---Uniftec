package br.com.uniftec.ecommercemobile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioResponse {

    private Long id;
    private String cpf;
    private String email;
    private String nome;
    private String senha;
    private String telefone;
    private List<UsuarioEnderecoResponse> enderecos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<UsuarioEnderecoResponse> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<UsuarioEnderecoResponse> enderecos) {
        this.enderecos = enderecos;
    }
}
