package br.com.uniftec.ecommercemobile.task;

import android.os.AsyncTask;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.EcommerceResponseStatus;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import br.com.uniftec.ecommercemobile.services.UsuarioService;
import br.com.uniftec.ecommercemobile.util.ServerCommunicationUtil;
import retrofit2.Call;

public class RemoverUsuarioEnderecoTask extends AsyncTask<Object, Void, EcommerceResponse<UsuarioResponse>>{

    private RemoverUsuarioEnderecoDelegate delegate;
    private UsuarioService usuarioService;

    public RemoverUsuarioEnderecoTask(RemoverUsuarioEnderecoDelegate delegate) {
        this.usuarioService = ServerCommunicationUtil
                .getInstance()
                .getRetrofit()
                .create(UsuarioService.class);

        this.delegate = delegate;
    }

    @Override
    protected EcommerceResponse<UsuarioResponse> doInBackground(Object... parameters) {
        Call<EcommerceResponse<UsuarioResponse>> call = usuarioService.removerUsuarioEndereco((String) parameters[0], (String) parameters[1]);

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
            delegate.removerUsuarioEnderecoSucesso(response.getData());
        } else {
            delegate.removerUsuarioEnderecoFalha(response.getMessage());
        }
    }

    public interface RemoverUsuarioEnderecoDelegate {
        public void removerUsuarioEnderecoSucesso(UsuarioResponse usuarioResponse);
        public void removerUsuarioEnderecoFalha(String mensagem);
    }
}
