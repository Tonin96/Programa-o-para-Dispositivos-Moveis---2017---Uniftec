package br.com.uniftec.fteclistview.service;

import br.com.uniftec.fteclistview.model.PopularResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("/3/movie/popular")
    public Call<PopularResponse> carregarPopulares(@Query("api_key") String apiKey);

}
