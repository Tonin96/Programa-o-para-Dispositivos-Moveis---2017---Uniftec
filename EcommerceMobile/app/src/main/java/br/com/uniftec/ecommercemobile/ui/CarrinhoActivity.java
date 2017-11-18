package br.com.uniftec.ecommercemobile.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.adapter.CarrinhoAdapter;
import br.com.uniftec.ecommercemobile.services.CarrinhoService;

public class CarrinhoActivity extends AbstractActivity implements View.OnClickListener {

    public static final String CARRINHO_PARAMETER = "CARRINHO_PARAMETER";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button finalizarCarrinho;

    public CarrinhoActivity(){
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_carrinho;
    }

    @Override
    protected void setupView() {

        getSupportActionBar().setTitle("Carrinho");

        final CarrinhoService carrinhoService = new CarrinhoService(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_carrinho);
        finalizarCarrinho = (Button) findViewById(R.id.actvity_carrinho_finalizar);

        finalizarCarrinho.setOnClickListener(this);
        getSupportActionBar().setSubtitle("Total R$: " + String.valueOf(carrinhoService.getValorCarrinho()));
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
                getSupportActionBar().setSubtitle("Total R$: " + String.valueOf(carrinhoService.getValorCarrinho()));
            }
        };
        recyclerView.getAdapter().registerAdapterDataObserver(dataSetObserver);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        final Intent intent;

        intent =  new Intent(v.getContext(), FinalizaCarrinhoActivity.class);
        //intent.putExtra(FinalizaCarrinhoActivity.FINALIZAR_CARRINHO_PARAMETER, ca);

        v.getContext().startActivity(intent);

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
