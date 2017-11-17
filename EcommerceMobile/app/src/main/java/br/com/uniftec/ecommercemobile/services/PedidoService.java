package br.com.uniftec.ecommercemobile.services;

import java.util.List;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.Pedido;
import br.com.uniftec.ecommercemobile.model.PedidoResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PedidoService {

    @GET("/pedido")
    public Call<EcommerceResponse<List<PedidoResponse>>> carregarPedidos(@Header("X-Token") String token);

    @PUT("/pedido")
    public Call<EcommerceResponse<PedidoResponse>> salvarPedido(@Header("X-Token") String token, @Body Pedido pedido);

    @POST("/pedido")
    public Call<EcommerceResponse<PedidoResponse>> atualizarPedido(@Path("id") Long id);

}
