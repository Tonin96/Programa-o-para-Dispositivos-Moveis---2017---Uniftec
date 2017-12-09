package br.com.uniftec.ecommercemobile.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.Pedido;
import br.com.uniftec.ecommercemobile.model.UsuarioEndereco;
import br.com.uniftec.ecommercemobile.model.UsuarioEnderecoResponse;

public class PedidoActivity extends AbstractActivity {

    public static final String PEDIDO_PARAMETER = "PEDIDO_PARAMETER";

    private Pedido pedido;
    private UsuarioEnderecoResponse enderecoEntrega;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView titulo;
    private TextView data;
    private TextView status;
    private TextView bairro;
    private TextView cidade;
    private TextView complemento;
    private TextView estado;
    private TextView logradouro;
    private TextView numero;

    public PedidoActivity() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pedido;
    }

    @Override
    protected void setupView() {
        titulo = (TextView) findViewById(R.id.activity_pedido_titulo);
        data = (TextView) findViewById(R.id.activity_pedido_data);
        status = (TextView) findViewById(R.id.activity_pedido_status);
        bairro = (TextView) findViewById(R.id.activity_pedido_bairro);
        cidade = (TextView) findViewById(R.id.activity_pedido_cidade);
        complemento = (TextView) findViewById(R.id.activity_pedido_complemento);
        estado = (TextView) findViewById(R.id.activity_pedido_estado);
        logradouro = (TextView) findViewById(R.id.activity_pedido_logradouro);
        numero = (TextView) findViewById(R.id.activity_pedido_numero);

        pedido = (Pedido) getIntent().getSerializableExtra(PEDIDO_PARAMETER);
        enderecoEntrega = pedido.getEnderecoEntrega();

        titulo.setText(pedido.getId().toString());
        data.setText(pedido.getData());
        status.setText(pedido.getStatus());
        bairro.setText(enderecoEntrega.getBairro());
        cidade.setText(enderecoEntrega.getCidade());
        complemento.setText(enderecoEntrega.getComplemento());
        estado.setText(enderecoEntrega.getEstado());
        logradouro.setText(enderecoEntrega.getLogradouro());
        numero.setText(enderecoEntrega.getNumero());

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
