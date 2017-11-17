package br.com.uniftec.ecommercemobile.services;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.Pedido;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PedidoService {

    @PUT("/pedido")
    public Call<EcommerceResponse<String>> salvarPedido(@Body Pedido pedido);

    @POST("/pedido")
    public Call<EcommerceResponse<UsuarioResponse>> atualizarPedido(@Body Pedido pedido, @Header("X-Token") String token);

}
