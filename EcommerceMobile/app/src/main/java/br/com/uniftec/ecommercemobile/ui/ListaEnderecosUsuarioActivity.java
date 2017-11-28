package br.com.uniftec.ecommercemobile.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.adapter.CarrinhoAdapter;
import br.com.uniftec.ecommercemobile.adapter.ListaEnderecoAdapter;
import br.com.uniftec.ecommercemobile.model.UsuarioEndereco;
import br.com.uniftec.ecommercemobile.model.UsuarioEnderecoResponse;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import br.com.uniftec.ecommercemobile.services.CarrinhoService;

public class ListaEnderecosUsuarioActivity extends AbstractActivity
        implements
        View.OnClickListener
{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button novoEndereco;
    protected SharedPreferences preferences;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_lista_enderecos_usuario;
    }

    @Override
    protected void setupView() {

        getSupportActionBar().setTitle("Lista de Endereços");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_lista_enderecos_usuario);
        novoEndereco = (Button) findViewById(R.id.activity_lista_endereco_usuario_adicionar_endereco);

        preferences = this.getSharedPreferences("usuario_preferences", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String usuario = preferences.getString("usuario", "null");
        if(!usuario.equals("null")) {
            retornoJsonUsuarioResponse = gson.fromJson(usuario, UsuarioResponse.class);
        }

        ArrayList<UsuarioEndereco> usuarioEnderecoArrayList = new ArrayList<UsuarioEndereco>();
        for (UsuarioEnderecoResponse usuarioEnderecoResponse : retornoJsonUsuarioResponse.getEnderecos()) {
            usuarioEnderecoArrayList.add(usuarioEnderecoResponse.getEndereco());
        }

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ListaEnderecoAdapter(usuarioEnderecoArrayList, getBaseContext());
        recyclerView.setAdapter(mAdapter);
        novoEndereco.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        final Intent intent;

        intent = new Intent(v.getContext(), CriaEnderecoUsuarioActivity.class);

        v.getContext().startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}