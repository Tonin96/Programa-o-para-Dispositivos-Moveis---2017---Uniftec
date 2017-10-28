package br.com.uniftec.ecommercemobile.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.uniftec.ecommercemobile.MainActivity;
import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.adapter.ListaProdutoAdapter;
import br.com.uniftec.ecommercemobile.adapter.MyAdapter;
import br.com.uniftec.ecommercemobile.model.Produto;

public class ListaProdutoActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produto);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_list_produto);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<Produto> input = new ArrayList<Produto>();
        for (int i = 0; i < 100; i++) {
            Produto produto = new Produto();
            produto.setTitulo("teste" + i);
            produto.setPreco(2.0);
            input.add(produto);
        }// define an adapter
        mAdapter = new ListaProdutoAdapter(input);
        recyclerView.setAdapter(mAdapter);

    }
}
