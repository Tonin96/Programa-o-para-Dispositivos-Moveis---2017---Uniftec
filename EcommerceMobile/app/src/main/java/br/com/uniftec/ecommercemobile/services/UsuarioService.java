package br.com.uniftec.ecommercemobile.services;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.Usuario;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UsuarioService {

    @GET("/usuario")
    public Call<UsuarioResponse> carregarUsuario(@Header("X-Token") String token);

    @PUT("/usuario")
    public Call<EcommerceResponse<String>> salvarUsuario(@Body Usuario usuario);

    @POST("/usuario")
    public Call<EcommerceResponse<UsuarioResponse>> atualizarUsuario(@Body Usuario usuario, @Header("X-Token") String token);

}
