package br.com.uniftec.ecommercemobile.ui;

import android.app.Activity;
import android.app.ProgressDialog;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.adapter.ListaProdutoAdapter;
import br.com.uniftec.ecommercemobile.model.Produto;
import br.com.uniftec.ecommercemobile.model.ProdutoResponse;
import br.com.uniftec.ecommercemobile.task.CarregarProdutosTask;

public class ListaProdutoFragment extends Fragment implements CarregarProdutosTask.CarregarProdutosDelegate {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;
    private List<Produto> produtos = new ArrayList<Produto>();

    public ListaProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Lista de Produtos");
        View view = inflater.inflate(R.layout.fragment_lista_produto, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_list_produto);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        produtos.clear();
        mAdapter = new ListaProdutoAdapter(this.produtos);
        recyclerView.setAdapter(mAdapter);

        //progressDialog = ProgressDialog.show(getActivity(), "Aguarde", "Carregando Produtos", true, false);

        CarregarProdutosTask task = new CarregarProdutosTask(this);
        Object[] parametros = new Object[2];

        parametros[0] = null;
        parametros[1] = Long.valueOf(1);
        task.execute(parametros);
        progressDialog(this.getActivity(), "Carregando");

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void carregarProdutosSucesso(List<ProdutoResponse> listEcommerceResponse) {
        this.produtos.clear();

        for (ProdutoResponse produtoResponse : listEcommerceResponse) {
            this.produtos.add(produtoResponse.getProduto());
        }

        this.mAdapter.notifyDataSetChanged();
        dismisProgressDialog();
    }

    @Override
    public void carregarProdutosFalha(String mensagem) {
        dismisProgressDialog();
    }

    public void progressDialog(Activity activity, String mensagem) {
        progressDialog = ProgressDialog.show(activity, "Aguarde", mensagem, true, false);
    }

    public void dismisProgressDialog() {
        progressDialog.dismiss();
        progressDialog = null;
    }
}


