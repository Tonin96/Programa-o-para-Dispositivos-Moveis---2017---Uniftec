package br.com.uniftec.ecommercemobile.task;

import android.os.AsyncTask;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.EcommerceResponseStatus;
import br.com.uniftec.ecommercemobile.model.UsuarioEndereco;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import br.com.uniftec.ecommercemobile.services.UsuarioService;
import br.com.uniftec.ecommercemobile.util.ServerCommunicationUtil;
import retrofit2.Call;

public class SalvarUsuarioEnderecoTask extends AsyncTask<Object, Void, EcommerceResponse<UsuarioResponse>>{

    private SalvarUsuarioEnderecoDelegate delegate;
    private UsuarioService usuarioService;

    public SalvarUsuarioEnderecoTask(SalvarUsuarioEnderecoDelegate delegate) {
        this.usuarioService = ServerCommunicationUtil
                .getInstance()
                .getRetrofit()
                .create(UsuarioService.class);

        this.delegate = delegate;
    }

    @Override
    protected EcommerceResponse<UsuarioResponse> doInBackground(Object... parameters) {
        Call<EcommerceResponse<UsuarioResponse>> call = usuarioService.salvarUsuarioEndereco((String) parameters[0], (UsuarioEndereco) parameters[1]);

        try {
            retrofit2.Response<EcommerceResponse<UsuarioResponse>> response = call.execute();

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(EcommerceResponse<UsuarioResponse> response) {
        if(response.getStatus() == EcommerceResponseStatus.SUCCESS) {
            delegate.salvarUsuarioEnderecoSucesso(response.getData());
        } else {
            delegate.salvarUsuarioEnderecoFalha(response.getMessage());
        }
    }

    public interface SalvarUsuarioEnderecoDelegate {
        public void salvarUsuarioEnderecoSucesso(UsuarioResponse usuarioResponse);
        public void salvarUsuarioEnderecoFalha(String mensagem);
    }
}
