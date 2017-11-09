package br.com.uniftec.ecommercemobile.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import org.json.JSONException;

import java.util.List;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.adapter.CarrinhoAdapter;
import br.com.uniftec.ecommercemobile.adapter.ListaProdutoAdapter;
import br.com.uniftec.ecommercemobile.model.Carrinho;
import br.com.uniftec.ecommercemobile.model.Produto;

public class CarrinhoActivity extends AbstractActivity {

    public static final String CARRINHO_PARAMETER = "CARRINHO_PARAMETER";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Carrinho carrinho;

    public CarrinhoActivity(){
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_carrinho;
    }

    @Override
    protected void setupView() {
        carrinho = (Carrinho) getIntent().getSerializableExtra(CARRINHO_PARAMETER);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_lcarrinho);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new CarrinhoAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
