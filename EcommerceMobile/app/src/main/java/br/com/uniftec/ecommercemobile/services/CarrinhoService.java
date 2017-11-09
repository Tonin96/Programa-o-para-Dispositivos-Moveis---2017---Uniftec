package br.com.uniftec.ecommercemobile.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import br.com.uniftec.ecommercemobile.model.Produto;

/**
 * Created by bruno on 08/11/17.
 */

public class CarrinhoService {

    private final String TAG = "carrinho";
    private Context context;

    public CarrinhoService(Context context) {
        this.context = context;
    }

    public void adicionarProduto(Produto produto) {
        List<Produto> produtos = buscaProdutos();
        produtos.add(produto);

        setList(this.TAG, produtos);
    }

    public void removeProduto(Produto produto) {
        ArrayList<Produto> produtos =  buscaProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto_teste = produtos.get(i);
            if (produto_teste.getId() == produto.getId()) {
                produtos.remove(i);
            }
        }

        setList(TAG, produtos);
    }

    public ArrayList<Produto> buscaProdutos() {
        HashMap<String, String> hashMapCarrinho = (HashMap<String, String>) context.getSharedPreferences(TAG, Context.MODE_PRIVATE).getAll();
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Produto>>() {
        }.getType();

        ArrayList<Produto> produtos = gson.fromJson(hashMapCarrinho.get(TAG), listType);
        if(produtos == null){
            return new ArrayList<Produto>();
        }else{
            return gson.fromJson(hashMapCarrinho.get(TAG), listType);
        }
    }

    private <T> void setList(String key, List<Produto> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        set(key, json);
    }

    private void set(String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("carrinho", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
