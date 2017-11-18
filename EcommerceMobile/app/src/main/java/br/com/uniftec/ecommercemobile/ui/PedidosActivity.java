package br.com.uniftec.ecommercemobile.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONException;

import java.util.ArrayList;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.adapter.ListaPedidoProdutoAdapter;
import br.com.uniftec.ecommercemobile.adapter.ListaProdutoAdapter;
import br.com.uniftec.ecommercemobile.model.Pedido;
import br.com.uniftec.ecommercemobile.model.PedidoProduto;
import br.com.uniftec.ecommercemobile.model.Produto;

public class PedidosActivity extends AbstractActivity {
    public static final String PEDIDO_PARAMETER = "PEDIDO_PARAMETER";

    private Pedido pedido;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public PedidosActivity() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pedidos;
    }

    @Override
    protected void setupView() {
        pedido = (Pedido) getIntent().getSerializableExtra(PEDIDO_PARAMETER);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_pedido);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListaPedidoProdutoAdapter(pedido.getItens());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
