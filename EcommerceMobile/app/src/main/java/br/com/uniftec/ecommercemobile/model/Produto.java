package br.com.uniftec.ecommercemobile.model;

import android.media.Image;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bruno on 05/10/17.
 */

public class Produto implements Serializable {

    private Integer id;
    private String titulo;
    private String descricao;
    private String imagem_principal;
    private List<Image> lista_imagens;
    private Double preco;
    private Double preco_desconto;
    private Integer categoria_id;

    public Integer getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Integer categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem_principal() {
        return imagem_principal;
    }

    public void setImagem_principal(String imagem_principal) {
        this.imagem_principal = imagem_principal;
    }

    public List<Image> getLista_imagens() {
        return lista_imagens;
    }

    public void setLista_imagens(List<Image> lista_imagens) {
        this.lista_imagens = lista_imagens;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getPreco_desconto() {
        return preco_desconto;
    }

    public void setPreco_desconto(Double preco_desconto) {
        this.preco_desconto = preco_desconto;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
