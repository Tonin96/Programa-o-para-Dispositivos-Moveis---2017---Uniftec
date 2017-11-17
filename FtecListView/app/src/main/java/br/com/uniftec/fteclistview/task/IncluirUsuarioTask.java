package br.com.uniftec.fteclistview.task;

import android.os.AsyncTask;

import br.com.uniftec.fteclistview.model.PopularResponse;
import br.com.uniftec.fteclistview.model.Response;
import br.com.uniftec.fteclistview.model.ResponseStatus;
import br.com.uniftec.fteclistview.model.Usuario;
import br.com.uniftec.fteclistview.service.UsuarioService;
import br.com.uniftec.fteclistview.util.ServerCommunicationUtil;
import retrofit2.Call;

public class IncluirUsuarioTask extends AsyncTask<Usuario, Void, Response<String>> {

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
    protected Response<String> doInBackground(Usuario... parameters) {

        Call<Response<String>> call = usuarioService.salvarUsuario(parameters[0]);

        try {
            retrofit2.Response<Response<String>> response = call.execute();

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Response<String> response) {

        if(response.getStatus() == ResponseStatus.SUCCESS) {
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
