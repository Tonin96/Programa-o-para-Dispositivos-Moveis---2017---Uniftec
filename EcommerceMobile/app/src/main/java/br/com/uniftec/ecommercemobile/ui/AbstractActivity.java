package br.com.uniftec.ecommercemobile.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;

public abstract class AbstractActivity extends AppCompatActivity implements View.OnClickListener{

    protected ActionBar actionBar;
    protected SharedPreferences preferences;
    protected UsuarioResponse retornoJsonUsuarioResponse;

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
            TextView meusDados = (TextView) view.findViewById(R.id.main_navigation_header_usuario_meus_dados);
            TextView nome = (TextView) view.findViewById(R.id.main_navigation_header_usuario_nome);

            /*Pega os dados de login*/
            preferences = this.getSharedPreferences("usuario_preferences", Context.MODE_PRIVATE);

            /*Converte o usuario armazenado no momento do login para UsuarioResponse*/
            Gson gson = new Gson();
            String usuario = preferences.getString("usuario", "null");
            if(!usuario.equals("null")) {
                retornoJsonUsuarioResponse = gson.fromJson(usuario, UsuarioResponse.class);

            /*Troca o nome do usuário na barra lateral*/
                nome.setText(retornoJsonUsuarioResponse.getNome());
            }

            meusDados.setOnClickListener(this);
        }
    }

    public void onClick(View v){
        Intent intent =  new Intent(this, AlteraContaUsuarioActivity.class);

        this.startActivity(intent);
    }

    protected abstract int getLayoutRes();
    protected abstract void setupView();
}