package br.com.uniftec.ecommercemobile.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONException;

import br.com.uniftec.ecommercemobile.R;

public class PedidosActivity extends AbstractActivity implements View.OnClickListener {
    public static final String PEDIDO_PARAMETER = "PEDIDO_PARAMETER";


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pedidos;
    }

    @Override
    protected void setupView() {

    }

    @Override
    public void onClick(View v) {

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
