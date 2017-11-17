package br.com.uniftec.ecommercemobile.services;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.Login;
import br.com.uniftec.ecommercemobile.model.Usuario;
import br.com.uniftec.ecommercemobile.model.UsuarioEndereco;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioService {

    @GET("/usuario")
    public Call<EcommerceResponse<UsuarioResponse>> carregarUsuario(@Header("X-Token") String token);

    @POST("/usuario")
    public Call<EcommerceResponse<UsuarioResponse>> atualizarUsuario(@Header("X-Token") String token, @Body Usuario usuario);

    @PUT("/usuario")
    public Call<EcommerceResponse<String>> salvarUsuario(@Body Usuario usuario);

    @PUT("/usuario/endereco")
    public Call<EcommerceResponse<UsuarioResponse>> salvarUsuarioEndereco(@Header("X-Token") String token, @Body UsuarioEndereco usuarioEndereco);

    @PUT("/usuario/endereco")
    public Call<EcommerceResponse<UsuarioResponse>> removerUsuarioEndereco(@Header("X-Token") String token, @Path("id") String id);

    @POST("/usuario/login")
    public Call<EcommerceResponse<String>> loginUsuario(@Body Login login);
}
