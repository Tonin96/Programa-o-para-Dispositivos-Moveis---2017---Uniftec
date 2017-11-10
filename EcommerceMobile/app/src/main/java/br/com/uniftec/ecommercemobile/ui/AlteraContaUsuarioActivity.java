package br.com.uniftec.ecommercemobile.ui;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import br.com.uniftec.ecommercemobile.R;

public class AlteraContaUsuarioActivity extends AbstractActivity implements View.OnClickListener {
    private Button buttonEndereco;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_altera_conta_usuario;
    }

    @Override
    protected void setupView() {

        getSupportActionBar().setTitle("Minha Conta");

        buttonEndereco = (Button) findViewById(R.id.activity_altera_conta_usuario_button_endereco);
        buttonEndereco.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, new ListaEnderecosFragment());
        transaction.addToBackStack(null);
        transaction.commit();
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
