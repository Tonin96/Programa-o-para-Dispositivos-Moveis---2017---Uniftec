package br.com.uniftec.ecommercemobile.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.adapter.ListaPedidosAdapter;
import br.com.uniftec.ecommercemobile.adapter.ListaProdutoAdapter;
import br.com.uniftec.ecommercemobile.model.Carrinho;
import br.com.uniftec.ecommercemobile.model.Pedido;
import br.com.uniftec.ecommercemobile.model.PedidoProduto;
import br.com.uniftec.ecommercemobile.model.PedidoResponse;
import br.com.uniftec.ecommercemobile.model.Produto;
import br.com.uniftec.ecommercemobile.model.ProdutoImagem;
import br.com.uniftec.ecommercemobile.task.CarregarPedidosTask;

public class ListaPedidosFragment extends Fragment implements CarregarPedidosTask.CarregarPedidosDelegate{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;
    private SharedPreferences preferences;
    private String token;
    private List<Pedido> pedidos = new ArrayList<Pedido>();

    public ListaPedidosFragment() {
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Lista de Pedidos");

        View view = inflater.inflate(R.layout.fragment_lista_pedidos, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_list_pedidos);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        preferences = view.getContext().getSharedPreferences("usuario_preferences", Context.MODE_PRIVATE);
        this.token = preferences.getString("X-Token", "null");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        pedidos.clear();
        mAdapter = new ListaPedidosAdapter(pedidos, getContext());

        recyclerView.setAdapter(mAdapter);

        this.progressDialog(getActivity(), "Carregando Pedidos");

        CarregarPedidosTask carregarPedidosTask = new CarregarPedidosTask(this);

        carregarPedidosTask.execute(this.token);

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void carregarPedidosSucesso(List<PedidoResponse> listEcommerceResponse) {

        this.pedidos.clear();

        for (PedidoResponse pedidoResponse : listEcommerceResponse) {
            this.pedidos.add(pedidoResponse.getPedido());
        }

        this.mAdapter.notifyDataSetChanged();

        dismisProgressDialog();
        Toast.makeText(getActivity(), "Pedidos carregados com sucesso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void carregarPedidosFalha(String mensagem) {
        dismisProgressDialog();
        Toast.makeText(getActivity(), mensagem, Toast.LENGTH_SHORT).show();
    }

    private void progressDialog(Context context, String mensagem) {
        progressDialog = ProgressDialog.show(context, "Aguarde", mensagem, true, false);
    }

    private void dismisProgressDialog() {
        progressDialog.dismiss();
        progressDialog = null;
    }
}
