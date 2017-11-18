package br.com.uniftec.ecommercemobile.task;

import android.os.AsyncTask;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.EcommerceResponseStatus;
import br.com.uniftec.ecommercemobile.model.Pedido;
import br.com.uniftec.ecommercemobile.model.PedidoResponse;
import br.com.uniftec.ecommercemobile.services.PedidoService;
import br.com.uniftec.ecommercemobile.util.ServerCommunicationUtil;
import retrofit2.Call;

public class SalvarPedidoTask extends AsyncTask<Object, Void, EcommerceResponse<PedidoResponse>>{

    private SalvarPedidoDelegate delegate;
    private PedidoService pedidoService;

    public SalvarPedidoTask(SalvarPedidoDelegate delegate) {
        this.pedidoService = ServerCommunicationUtil
                .getInstance()
                .getRetrofit()
                .create(PedidoService.class);

        this.delegate = delegate;
    }

    @Override
    protected EcommerceResponse<PedidoResponse> doInBackground(Object... parameters) {
        Call<EcommerceResponse<PedidoResponse>> call = pedidoService.salvarPedido((String) parameters[0], (Pedido) parameters[1]);

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
            delegate.salvarPedidoSucesso(response.getData());
        } else {
            delegate.salvarPedidoFalha(response.getMessage());
        }
    }

    public interface SalvarPedidoDelegate {
        public void salvarPedidoSucesso(PedidoResponse pedidoResponse);
        public void salvarPedidoFalha(String mensagem);
    }
}
