package br.com.uniftec.fteclistview.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by marioklein on 21/09/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Filme implements Serializable{

    @JsonProperty("title")
    private String titulo;

    @JsonProperty("vote_count")
    private int votos;

    @JsonProperty("vote_average")
    private double nota;

    @JsonProperty("overview")
    private String resumo;

    @JsonProperty("release_date")
    private String lancamento;

    @JsonProperty("poster_path")
    private String imagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
