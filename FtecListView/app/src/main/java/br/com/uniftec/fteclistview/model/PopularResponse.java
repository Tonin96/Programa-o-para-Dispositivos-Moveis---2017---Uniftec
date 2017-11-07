package br.com.uniftec.fteclistview.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PopularResponse {

    @JsonProperty("page")
    private int pagina;

    @JsonProperty("total_results")
    private int totalResultado;

    @JsonProperty("total_pages")
    private int totalPaginas;

    @JsonProperty("results")
    private List<Filme> filmes;


    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int getTotalResultado() {
        return totalResultado;
    }

    public void setTotalResultado(int totalResultado) {
        this.totalResultado = totalResultado;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }
}
