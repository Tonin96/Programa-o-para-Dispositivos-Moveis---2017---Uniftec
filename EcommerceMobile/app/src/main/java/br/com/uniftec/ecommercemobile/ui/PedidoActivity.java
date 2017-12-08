package br.com.uniftec.ecommercemobile.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.Pedido;

public class PedidoActivity extends AbstractActivity {

    public static final String PEDIDO_PARAMETER = "PEDIDO_PARAMETER";

    private Pedido pedido;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView tituloPedido;

    public PedidoActivity() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pedidos;
    }

    @Override
    protected void setupView() {
        tituloPedido = (TextView) findViewById(R.id.activity_pedido_titulo);

        pedido = (Pedido) getIntent().getSerializableExtra(PEDIDO_PARAMETER);

        tituloPedido.setText("Pedido " + pedido.getStatus());

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_pedido);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
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
