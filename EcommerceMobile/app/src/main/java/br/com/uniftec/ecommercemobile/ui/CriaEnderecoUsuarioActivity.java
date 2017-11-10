package br.com.uniftec.ecommercemobile.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import br.com.uniftec.ecommercemobile.R;

public class CriaEnderecoUsuarioActivity extends AbstractActivity {
        @Override
    protected int getLayoutRes() {
        return R.layout.activity_cria_endereco_usuario;
    }

    @Override
    protected void setupView() {

        getSupportActionBar().setTitle("Novo Endereço");

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
