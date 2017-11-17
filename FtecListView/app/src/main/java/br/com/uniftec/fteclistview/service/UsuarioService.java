package br.com.uniftec.fteclistview.service;

import br.com.uniftec.fteclistview.model.Response;
import br.com.uniftec.fteclistview.model.Usuario;
import br.com.uniftec.fteclistview.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UsuarioService {

    @PUT("/usuario")
    public Call<Response<String>> salvarUsuario(@Body Usuario usuario);


    /*Quando for passar mais do que um parametro pro doInBackground, passar Object e fazer o cast*/
    @POST("/usuario")
    public Call<Response<UsuarioResponse>> atualizarUsuario(@Body Usuario usuario, @Header("X-Token") String token);

}
