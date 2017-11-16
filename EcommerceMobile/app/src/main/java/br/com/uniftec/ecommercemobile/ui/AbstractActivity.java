package br.com.uniftec.ecommercemobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import br.com.uniftec.ecommercemobile.R;

/**
 * Created by bruno on 05/10/17.
 */

public abstract class AbstractActivity extends AppCompatActivity implements View.OnClickListener{

    protected ActionBar actionBar;

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        NavigationView navigationView = (NavigationView) findViewById(R.id.main_navigation_view);
        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setupView();

        if(navigationView != null) {
            View view = navigationView.getHeaderView(0);
            TextView textViewMeuPerfil = (TextView) view.findViewById(R.id.main_navigation_header_usuario_meu_perfil);
            textViewMeuPerfil.setOnClickListener(this);
        }
    }

    public void onClick(View v){
        Intent intent =  new Intent(this, AlteraContaUsuarioActivity.class);

        this.startActivity(intent);
    }

    protected abstract int getLayoutRes();

    protected abstract void setupView();
}
