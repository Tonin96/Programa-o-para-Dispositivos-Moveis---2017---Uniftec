package br.com.uniftec.ecommercemobile.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.uniftec.ecommercemobile.R;

public class ProdutoActivity extends AppCompatActivity {

    public static final String PRODUTO_PARAMETER = "PRODUTO_PARAMETER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
    }
}
