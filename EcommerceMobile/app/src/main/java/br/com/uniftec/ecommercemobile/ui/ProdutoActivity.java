package br.com.uniftec.ecommercemobile.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.Carrinho;
import br.com.uniftec.ecommercemobile.model.Produto;
import br.com.uniftec.ecommercemobile.services.CarrinhoService;

import static java.security.AccessController.getContext;

public class ProdutoActivity extends AbstractActivity implements View.OnClickListener{

    public static final String PRODUTO_PARAMETER = "PRODUTO_PARAMETER";

    private Produto produto;
    private TextView tituloTextView;
    private TextView precoTextView;
    private TextView precoDescontoTextView;
    private TextView descricaoTextView;
    private TextView idTextView;
    private ImageView imagemPrincipalImageView;
    private Button botaoComprar;

    @Override
    protected void setupView() {
        idTextView = (TextView) findViewById(R.id.activity_produto_id);
        tituloTextView = (TextView) findViewById(R.id.activity_produto_titulo);
        precoTextView = (TextView) findViewById(R.id.activity_produto_preco);
        precoDescontoTextView = (TextView) findViewById(R.id.activity_produto_preco_desconto);
        descricaoTextView = (TextView) findViewById(R.id.activity_produto_descricao);
        imagemPrincipalImageView = (ImageView) findViewById(R.id.activity_produto_imagen_principal);
        botaoComprar = (Button) findViewById(R.id.activity_produto_botao_comprar);


        produto = (Produto) getIntent().getSerializableExtra(PRODUTO_PARAMETER);

        botaoComprar.setOnClickListener(this);
        idTextView.setText(produto.getId().toString());
        tituloTextView.setText(produto.getTitulo());
        precoTextView.setText(Double.toString(produto.getPreco()));
        precoDescontoTextView.setText(Double.toString(produto.getPreco_desconto()));
        descricaoTextView.setText(produto.getDescricao());

        int imagemPrincipal = getResources().getIdentifier(produto.getImagem_principal(), "drawable", getPackageName());
        imagemPrincipalImageView.setImageDrawable(getDrawable(imagemPrincipal));
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
