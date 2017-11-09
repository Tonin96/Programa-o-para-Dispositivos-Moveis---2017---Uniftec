package br.com.uniftec.ecommercemobile.ui;

import android.database.DataSetObserver;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.adapter.CarrinhoAdapter;
import br.com.uniftec.ecommercemobile.services.CarrinhoService;

public class CarrinhoActivity extends AbstractActivity {

    public static final String CARRINHO_PARAMETER = "CARRINHO_PARAMETER";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView textViewTotal;

    public CarrinhoActivity(){
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_carrinho;
    }

    @Override
    protected void setupView() {
        final CarrinhoService carrinhoService = new CarrinhoService(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_lcarrinho);
        textViewTotal = (TextView) findViewById(R.id.activity_carrinho_total);

        textViewTotal.setText(String.valueOf(carrinhoService.getValorCarrinho()));
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new CarrinhoAdapter(this);
        recyclerView.setAdapter(mAdapter);
        RecyclerView.AdapterDataObserver dataSetObserver = new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                textViewTotal.setText(String.valueOf(carrinhoService.getValorCarrinho()));
            }
        };
        recyclerView.getAdapter().registerAdapterDataObserver(dataSetObserver);
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
