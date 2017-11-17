package br.com.uniftec.ecommercemobile.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.adapter.ListaPedidosAdapter;
import br.com.uniftec.ecommercemobile.adapter.ListaProdutoAdapter;
import br.com.uniftec.ecommercemobile.model.Carrinho;
import br.com.uniftec.ecommercemobile.model.Pedido;
import br.com.uniftec.ecommercemobile.model.Produto;

/**
 * Created by bruno on 06/11/17.
 */

public class ListaPedidosFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public ListaPedidosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_pedidos, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_list_pedidos);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        Carrinho carrinho = new Carrinho();
        Random gerador = new Random();

        for (int i = 0; i < 100; i++) {
            Produto produto = new Produto();
            produto.setId(i);
            produto.setTitulo("Produto: " + i);
            produto.setPreco((double) gerador.nextInt(100));
            produto.setPreco_desconto(produto.getPreco() / 2);
            produto.setDescricao("O Produto: " + produto.getTitulo() + " é muito legal.");
            produto.setImagem_principal("ft_4gx0m4rifoqxbz9lejqq6wypqyo");
            carrinho.addProduto(produto);
        }// define an adapter


        Pedido pedido = new Pedido();
        pedido.setStatus(Pedido.STATUS_PEDIDO_ABERTO);
        Pedido pedido2 = new Pedido();
        pedido2.setStatus(Pedido.STATUS_PEDIDO_FECHADO);
        pedido.setCarrinho(carrinho);
        pedido2.setCarrinho(carrinho);
        pedido.setData(new Date());
        pedido2.setData(new Date());
        pedidos.add(pedido);
        pedidos.add(pedido2);
        mAdapter = new ListaPedidosAdapter(pedidos);
        recyclerView.setAdapter(mAdapter);


        super.onActivityCreated(savedInstanceState);
    }
}
