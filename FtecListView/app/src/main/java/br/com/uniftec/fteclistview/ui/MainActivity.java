package br.com.uniftec.fteclistview.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.uniftec.fteclistview.DataSource;
import br.com.uniftec.fteclistview.R;
import br.com.uniftec.fteclistview.adapter.FilmeAdapter;
import br.com.uniftec.fteclistview.model.Filme;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listViewFilmes;
    private FilmeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewFilmes = (ListView)findViewById(R.id.list_view_filmes);

        List<Filme> filmes = DataSource.carregarFilmes(this);

        adapter = new FilmeAdapter(this, 0, filmes);

        listViewFilmes.setAdapter(adapter);
        listViewFilmes.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Filme filme = adapter.getItem(position);

        Intent intent = new Intent(this, FilmeActivity.class);

        intent.putExtra(FilmeActivity.FILME_PARAMETER, filme);

        startActivity(intent);

    }
}
