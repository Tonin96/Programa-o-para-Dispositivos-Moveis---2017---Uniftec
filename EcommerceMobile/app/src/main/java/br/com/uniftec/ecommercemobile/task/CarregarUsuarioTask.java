package br.com.uniftec.ecommercemobile.task;

import android.os.AsyncTask;

import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import br.com.uniftec.ecommercemobile.services.UsuarioService;
import br.com.uniftec.ecommercemobile.util.ServerCommunicationUtil;
import retrofit2.Call;
import retrofit2.Response;

public class CarregarUsuarioTask extends AsyncTask<String, Void, UsuarioResponse>{

    private CarregarUsuarioDelegate delegate;
    private UsuarioService usuarioService;

    public CarregarUsuarioTask(CarregarUsuarioDelegate delegate) {

        this.usuarioService = ServerCommunicationUtil
                .getInstance()
                .getRetrofit()
                .create(UsuarioService.class);

        this.delegate = delegate;
    }

    @Override
    protected UsuarioResponse doInBackground(String... parameters) {

        Call<UsuarioResponse> call = usuarioService.carregarUsuario(parameters[0]);

        try {
            return call.execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(UsuarioResponse usuarioResponse) {

        if(usuarioResponse != null) {
            delegate.sucessoCarregarUsuario(usuarioResponse);
        } else {
            delegate.falhaCarregarUsuario("Não foi possível carregar o usuário");
        }

    }

    public interface CarregarUsuarioDelegate{
        public void sucessoCarregarUsuario(UsuarioResponse usuarioResponse);
        public void falhaCarregarUsuario(String mensagem);
    }
}
