package br.com.uniftec.ecommercemobile.task;

import android.os.AsyncTask;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.EcommerceResponseStatus;
import br.com.uniftec.ecommercemobile.model.Usuario;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import br.com.uniftec.ecommercemobile.services.UsuarioService;
import br.com.uniftec.ecommercemobile.util.ServerCommunicationUtil;
import retrofit2.Call;

public class AtualizarUsuarioTask extends AsyncTask<Object, Void, EcommerceResponse<UsuarioResponse>> {

    private AtualizarUsuarioDelegate delegate;
    private UsuarioService usuarioService;

    public AtualizarUsuarioTask(AtualizarUsuarioDelegate delegate) {
        this.usuarioService = ServerCommunicationUtil
                .getInstance()
                .getRetrofit()
                .create(UsuarioService.class);

        this.delegate = delegate;
    }

    @Override
    protected EcommerceResponse<UsuarioResponse> doInBackground(Object... parameters) {
        Call<EcommerceResponse<UsuarioResponse>> call = usuarioService.atualizarUsuario((String) parameters[0], (Usuario) parameters[1]);

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
            delegate.atualizarUsuarioSucesso(response.getData());
        } else {
            delegate.atualizarUsuarioFalha(response.getMessage());
        }
    }

    public interface AtualizarUsuarioDelegate {
        public void atualizarUsuarioSucesso(UsuarioResponse usuarioResponse);
        public void atualizarUsuarioFalha(String mensagem);
    }
}
