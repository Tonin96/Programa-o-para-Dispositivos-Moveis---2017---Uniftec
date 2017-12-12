package br.com.uniftec.ecommercemobile.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.adapter.ListaProdutoAdapter;
import br.com.uniftec.ecommercemobile.model.Produto;
import br.com.uniftec.ecommercemobile.model.ProdutoCategoria;
import br.com.uniftec.ecommercemobile.model.ProdutoCategoriaResponse;
import br.com.uniftec.ecommercemobile.model.ProdutoResponse;
import br.com.uniftec.ecommercemobile.task.CarregarCategoriasTask;
import br.com.uniftec.ecommercemobile.task.CarregarProdutosTask;

public class ListaProdutoFragment extends Fragment implements CarregarProdutosTask.CarregarProdutosDelegate, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, CarregarCategoriasTask.CarregarCategoriasDelegate {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;
    private List<Produto> produtos = new ArrayList<Produto>();
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Boolean destaques = true;
    private Long categoria = null;
    private Button botaoDestaques;

    public ListaProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Lista de Produtos");
        View view = inflater.inflate(R.layout.fragment_lista_produto, container, false);
        botaoDestaques = view.findViewById(R.id.lista_produtos_button_destaques);
        Button botaoCategorias = view.findViewById(R.id.lista_produtos_button_categorias);
        botaoDestaques.setOnClickListener(this);
        botaoCategorias.setOnClickListener(this);
        botaoDestaques.setText("Ocultar destaques");

        recyclerView = view.findViewById(R.id.recycler_view_list_produto);

        drawerLayout = (DrawerLayout) view.findViewById(R.id.produto_drawer_layout);
        navigationView = (NavigationView) view.findViewById(R.id.produto_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

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

        carregarProdutos();

        CarregarCategoriasTask task = new CarregarCategoriasTask(this);
        Object[] parametros = new Object[0];

        task.execute(parametros);

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

        if(progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        this.categoria = Long.valueOf(item.getTitle().toString().split(" - ")[0]);
        carregarProdutos();

        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.lista_produtos_button_destaques) {
            clickDestaque();
        } else {
            drawerLayout.openDrawer(GravityCompat.END);

            CarregarCategoriasTask task = new CarregarCategoriasTask(this);
            Object[] parametros = new Object[0];

            task.execute(parametros);
            progressDialog(this.getActivity(), "Carregando");
        }
    }

    private void clickDestaque() {
        this.destaques = !this.destaques;
        if(this.destaques){
            botaoDestaques.setText("Ocultar destaques");
        }else{
            botaoDestaques.setText("Mostrar destaques");
        }

        carregarProdutos();
    }

    private void carregarProdutos() {
        CarregarProdutosTask task = new CarregarProdutosTask(this);
        Object[] parametros = new Object[2];

        parametros[0] = !this.destaques ? null : true;
        parametros[1] = this.categoria;
        task.execute(parametros);
        progressDialog(this.getActivity(), "Carregando");
    }

    @Override
    public void carregarCategoriasSucesso(List<ProdutoCategoriaResponse> listEcommerceResponse) {
        navigationView.getMenu().clear();
        for (ProdutoCategoriaResponse produtoCategoriaResponse : listEcommerceResponse) {
            this.navigationView.getMenu().add(produtoCategoriaResponse.getId() + " - " + produtoCategoriaResponse.getNome());
        }
        dismisProgressDialog();
    }

    @Override
    public void carregarCategoriasFalha(String mensagem) {
        dismisProgressDialog();
    }
}


