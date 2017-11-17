package br.com.uniftec.ecommercemobile.services;

import java.util.List;

import br.com.uniftec.ecommercemobile.model.EcommerceResponse;
import br.com.uniftec.ecommercemobile.model.ProdutoCategoriaResponse;
import br.com.uniftec.ecommercemobile.model.ProdutoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProdutoService {

    @GET("/produto")
    public Call<EcommerceResponse<List<ProdutoResponse>>> carregarProdutos(@Query("destaques") Boolean destaques, @Query("categoria-id") Long categoriaId);

    @GET("/produto/categorias")
    public Call<EcommerceResponse<List<ProdutoCategoriaResponse>>> carregarCategorias();

}
