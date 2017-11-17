package br.com.uniftec.ecommercemobile.task;

import android.os.AsyncTask;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import br.com.uniftec.ecommercemobile.services.UsuarioService;
import br.com.uniftec.ecommercemobile.util.ServerCommunicationUtil;
import retrofit2.Call;
import retrofit2.Response;

public class CarregarUsuarioTask extends AsyncTask<String, Void, EcommerceResponse<UsuarioResponse>>{

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
    protected EcommerceResponse<UsuarioResponse> doInBackground(String... parameters) {

        Call<EcommerceResponse<UsuarioResponse>> call = usuarioService.carregarUsuario(parameters[0]);

        try {
            Response<EcommerceResponse<UsuarioResponse>> response = call.execute();

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(EcommerceResponse<UsuarioResponse> response) {

        if(response != null) {
            delegate.sucessoCarregarUsuario(response.getData());
        } else {
            delegate.falhaCarregarUsuario(response.getMessage());
        }

    }

    public interface CarregarUsuarioDelegate{
        public void sucessoCarregarUsuario(UsuarioResponse usuarioResponse);
        public void falhaCarregarUsuario(String mensagem);
    }
}
