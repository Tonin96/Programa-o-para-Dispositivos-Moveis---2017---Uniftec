package br.com.uniftec.ecommercemobile.task;

import android.os.AsyncTask;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.EcommerceResponseStatus;
import br.com.uniftec.ecommercemobile.model.Usuario;
import br.com.uniftec.ecommercemobile.services.UsuarioService;
import br.com.uniftec.ecommercemobile.util.ServerCommunicationUtil;
import retrofit2.Call;

public class IncluirUsuarioTask extends AsyncTask<Usuario, Void, EcommerceResponse<String>> {

    private IncluirUsuarioDelegate delegate;
    private UsuarioService usuarioService;

    public IncluirUsuarioTask(IncluirUsuarioDelegate delegate) {

        this.usuarioService = ServerCommunicationUtil
                .getInstance()
                .getRetrofit()
                .create(UsuarioService.class);

        this.delegate = delegate;
    }

    @Override
    protected EcommerceResponse<String> doInBackground(Usuario... parameters) {

        Call<EcommerceResponse<String>> call = usuarioService.salvarUsuario(parameters[0]);

        try {
            retrofit2.Response<EcommerceResponse<String>> response = call.execute();

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(EcommerceResponse<String> response) {

        if(response.getStatus() == EcommerceResponseStatus.SUCCESS) {
            delegate.incluirUsuarioSucesso(response.getData());
        } else {
            delegate.incluirUsuarioSucesso(response.getMessage());
        }
    }

    public interface IncluirUsuarioDelegate {
        public void incluirUsuarioSucesso(String token);
        public void incluirUsuarioFalha(String mensagem);
    }
}
