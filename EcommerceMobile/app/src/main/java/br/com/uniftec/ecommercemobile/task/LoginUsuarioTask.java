package br.com.uniftec.ecommercemobile.task;

import android.os.AsyncTask;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.EcommerceResponseStatus;
import br.com.uniftec.ecommercemobile.model.Login;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import br.com.uniftec.ecommercemobile.services.UsuarioService;
import br.com.uniftec.ecommercemobile.util.ServerCommunicationUtil;
import retrofit2.Call;

public class LoginUsuarioTask extends AsyncTask<Login, Void, EcommerceResponse<String>>{

    private LoginUsuarioDelegate delegate;
    private UsuarioService usuarioService;

    public LoginUsuarioTask(LoginUsuarioDelegate delegate) {
        this.usuarioService = ServerCommunicationUtil
                .getInstance()
                .getRetrofit()
                .create(UsuarioService.class);

        this.delegate = delegate;
    }

    @Override
    protected EcommerceResponse<String> doInBackground(Login... parameters) {
        Call<EcommerceResponse<String>> call = usuarioService.loginUsuario(parameters[0]);

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
            delegate.loginUsuarioSucesso(response.getData());
        } else {
            delegate.loginUsuarioFalha(response.getMessage());
        }
    }

    public interface LoginUsuarioDelegate {
        public void loginUsuarioSucesso(String token);
        public void loginUsuarioFalha(String mensagem);
    }
}
