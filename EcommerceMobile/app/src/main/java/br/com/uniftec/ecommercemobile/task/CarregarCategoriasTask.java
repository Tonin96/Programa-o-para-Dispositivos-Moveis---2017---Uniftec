package br.com.uniftec.ecommercemobile.task;

import android.os.AsyncTask;

import java.util.List;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.EcommerceResponseStatus;
import br.com.uniftec.ecommercemobile.model.ProdutoCategoriaResponse;
import br.com.uniftec.ecommercemobile.services.ProdutoService;
import br.com.uniftec.ecommercemobile.util.ServerCommunicationUtil;
import retrofit2.Call;

public class CarregarCategoriasTask extends AsyncTask<Object, Void, EcommerceResponse<List<ProdutoCategoriaResponse>>>{

    private CarregarCategoriasDelegate delegate;
    private ProdutoService produtoService;

    public CarregarCategoriasTask(CarregarCategoriasDelegate delegate) {
        this.produtoService = ServerCommunicationUtil
                .getInstance()
                .getRetrofit()
                .create(ProdutoService.class);

        this.delegate = delegate;
    }

    @Override
    protected EcommerceResponse<List<ProdutoCategoriaResponse>> doInBackground(Object[] parameter) {
        Call<EcommerceResponse<List<ProdutoCategoriaResponse>>> call = produtoService.carregarCategorias();

        try {
            retrofit2.Response<EcommerceResponse<List<ProdutoCategoriaResponse>>> response = call.execute();

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(EcommerceResponse<List<ProdutoCategoriaResponse>> response) {
        if(response.getStatus() == EcommerceResponseStatus.SUCCESS) {
            delegate.carregarCategoriasSucesso(response.getData());
        } else {
            delegate.carregarCategoriasFalha(response.getMessage());
        }
    }

    public interface CarregarCategoriasDelegate {
        public void carregarCategoriasSucesso(List<ProdutoCategoriaResponse> listEcommerceResponse);
        public void carregarCategoriasFalha(String mensagem);
    }
}
