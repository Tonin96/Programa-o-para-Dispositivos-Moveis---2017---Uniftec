package br.com.uniftec.fteclistview.service;

import com.fasterxml.jackson.databind.JsonNode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PoaService {

    @GET("/php/facades/process.php")
    public Call<JsonNode> carregarIntinenarios(@Query("a") String a, @Query("p") String p);

}
