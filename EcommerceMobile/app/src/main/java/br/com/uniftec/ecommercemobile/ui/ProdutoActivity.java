package br.com.uniftec.ecommercemobile.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.adapter.ListaImagensAdapter;
import br.com.uniftec.ecommercemobile.adapter.ListaProdutoAdapter;
import br.com.uniftec.ecommercemobile.model.Produto;
import br.com.uniftec.ecommercemobile.services.CarrinhoService;

import static android.widget.LinearLayout.HORIZONTAL;

public class ProdutoActivity extends AbstractActivity implements View.OnClickListener{

    public static final String PRODUTO_PARAMETER = "PRODUTO_PARAMETER";

    private Produto produto;
    private TextView tituloTextView;
    private TextView precoTextView;
    private TextView precoDescontoTextView;
    private TextView descricaoTextView;
    private ImageView imagemPrincipalImageView;
    private Button botaoComprar;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void setupView() {
        tituloTextView = (TextView) findViewById(R.id.activity_produto_titulo);
        precoTextView = (TextView) findViewById(R.id.activity_produto_preco);
        precoDescontoTextView = (TextView) findViewById(R.id.activity_produto_preco_desconto);
        descricaoTextView = (TextView) findViewById(R.id.activity_produto_descricao);
        imagemPrincipalImageView = (ImageView) findViewById(R.id.activity_produto_imagen_principal);
        botaoComprar = (Button) findViewById(R.id.activity_produto_botao_comprar);


        produto = (Produto) getIntent().getSerializableExtra(PRODUTO_PARAMETER);

        getSupportActionBar().setTitle(produto.getNome());

        botaoComprar.setOnClickListener(this);
        tituloTextView.setText(produto.getNome());
        precoTextView.setText(String.valueOf(produto.getPreco()));

        if(produto.getPrecoDesconto() == null){
            precoDescontoTextView.setVisibility(View.GONE);
            TextView labelPrecoDescontoTextView = (TextView) findViewById(R.id.activity_produto_label_preco_desconto);
            labelPrecoDescontoTextView.setVisibility(View.GONE);

        }else{
            precoDescontoTextView.setText(Double.toString(produto.getPrecoDesconto()));
        }

        descricaoTextView.setText(produto.getDescricao());

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_list_imagens_produto);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this, HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListaImagensAdapter(produto.getImagens());
        recyclerView.setAdapter(mAdapter);

        Picasso.with(this).load(produto.getImagemPrincipal().getUrl()).into(imagemPrincipalImageView);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_produto;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        CarrinhoService carrinhoService = new CarrinhoService(this);

        carrinhoService.adicionarProduto(produto);
    }
}