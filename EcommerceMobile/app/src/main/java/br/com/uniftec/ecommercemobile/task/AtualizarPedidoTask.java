package br.com.uniftec.ecommercemobile.task;

import android.os.AsyncTask;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.EcommerceResponseStatus;
import br.com.uniftec.ecommercemobile.model.PedidoResponse;
import br.com.uniftec.ecommercemobile.services.PedidoService;
import br.com.uniftec.ecommercemobile.util.ServerCommunicationUtil;
import retrofit2.Call;

public class AtualizarPedidoTask extends AsyncTask<Long, Void, EcommerceResponse<PedidoResponse>>{

    private AtualizarPedidoDelegate delegate;
    private PedidoService pedidoService;

    public AtualizarPedidoTask(AtualizarPedidoDelegate delegate) {
        this.pedidoService = ServerCommunicationUtil
                .getInstance()
                .getRetrofit()
                .create(PedidoService.class);

        this.delegate = delegate;
    }

    @Override
    protected EcommerceResponse<PedidoResponse> doInBackground(Long... parameters) {
        Call<EcommerceResponse<PedidoResponse>> call = pedidoService.atualizarPedido(parameters[0]);

        try {
            retrofit2.Response<EcommerceResponse<PedidoResponse>> response = call.execute();

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(EcommerceResponse<PedidoResponse> response) {
        if(response.getStatus() == EcommerceResponseStatus.SUCCESS) {
            delegate.atualizarPedidoSucesso(response.getData());
        } else {
            delegate.atualizarPedidoFalha(response.getMessage());
        }
    }

    public interface AtualizarPedidoDelegate {
        public void atualizarPedidoSucesso(PedidoResponse pedidoResponse);
        public void atualizarPedidoFalha(String mensagem);
    }
}
