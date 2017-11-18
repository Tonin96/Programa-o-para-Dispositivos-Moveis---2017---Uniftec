package br.com.uniftec.ecommercemobile.task;

import android.os.AsyncTask;

import java.util.List;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.EcommerceResponseStatus;
import br.com.uniftec.ecommercemobile.model.PedidoResponse;
import br.com.uniftec.ecommercemobile.services.PedidoService;
import br.com.uniftec.ecommercemobile.util.ServerCommunicationUtil;
import retrofit2.Call;

public class CarregarPedidosTask extends AsyncTask<String, Void, EcommerceResponse<List<PedidoResponse>>>{

    private CarregarPedidosDelegate delegate;
    private PedidoService pedidoService;

    public CarregarPedidosTask(CarregarPedidosDelegate delegate) {
        this.pedidoService = ServerCommunicationUtil
                .getInstance()
                .getRetrofit()
                .create(PedidoService.class);

        this.delegate = delegate;
    }

    @Override
    protected EcommerceResponse<List<PedidoResponse>> doInBackground(String... parameters) {
        Call<EcommerceResponse<List<PedidoResponse>>> call = pedidoService.carregarPedidos(parameters[0]);

        try {
            retrofit2.Response<EcommerceResponse<List<PedidoResponse>>> response = call.execute();

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(EcommerceResponse<List<PedidoResponse>> response) {
        if(response.getStatus() == EcommerceResponseStatus.SUCCESS) {
            delegate.carregarPedidosSucesso(response.getData());
        } else {
            delegate.carregarPedidosFalha(response.getMessage());
        }
    }

    public interface CarregarPedidosDelegate {
        public void carregarPedidosSucesso(List<PedidoResponse> listEcommerceResponse);
        public void carregarPedidosFalha(String mensagem);
    }
}
