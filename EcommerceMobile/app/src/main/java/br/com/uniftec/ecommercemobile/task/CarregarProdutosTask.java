package br.com.uniftec.ecommercemobile.task;

import android.os.AsyncTask;

import java.util.List;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.EcommerceResponseStatus;
import br.com.uniftec.ecommercemobile.model.ProdutoResponse;
import br.com.uniftec.ecommercemobile.services.ProdutoService;
import br.com.uniftec.ecommercemobile.util.ServerCommunicationUtil;
import retrofit2.Call;

public class CarregarProdutosTask extends AsyncTask<Object, Void, EcommerceResponse<List<ProdutoResponse>>>{

    private CarregarProdutosDelegate delegate;
    private ProdutoService produtoService;

    public CarregarProdutosTask(CarregarProdutosDelegate delegate) {
        this.produtoService = ServerCommunicationUtil
                .getInstance()
                .getRetrofit()
                .create(ProdutoService.class);

        this.delegate = delegate;
    }

    @Override
    protected EcommerceResponse<List<ProdutoResponse>> doInBackground(Object... parameters) {
        Call<EcommerceResponse<List<ProdutoResponse>>> call = produtoService.carregarProdutos((Boolean) parameters[0], (Long) parameters[1]);

        try {
            retrofit2.Response<EcommerceResponse<List<ProdutoResponse>>> response = call.execute();

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(EcommerceResponse<List<ProdutoResponse>> response) {
        if(response.getStatus() == EcommerceResponseStatus.SUCCESS) {
            delegate.carregarProdutosSucesso(response.getData());
        } else {
            delegate.carregarProdutosFalha(response.getMessage());
        }
    }

    public interface CarregarProdutosDelegate {
        public void carregarProdutosSucesso(List<ProdutoResponse> listEcommerceResponse);
        public void carregarProdutosFalha(String mensagem);
    }
}
