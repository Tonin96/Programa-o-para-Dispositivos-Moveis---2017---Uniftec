package br.com.uniftec.fteclistview.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.uniftec.fteclistview.DataSource;
import br.com.uniftec.fteclistview.R;
import br.com.uniftec.fteclistview.adapter.FilmeAdapter;
import br.com.uniftec.fteclistview.model.Filme;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFilmesFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listViewFilmes;
    private FilmeAdapter adapter;

    public ListaFilmesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_filmes, container, false);

        listViewFilmes = (ListView)view.findViewById(R.id.list_view_filmes);
        listViewFilmes.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        List<Filme> filmes = DataSource.carregarFilmes(getActivity());

        adapter = new FilmeAdapter(getActivity(), 0, filmes);
        listViewFilmes.setAdapter(adapter);

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Filme filme = adapter.getItem(position);

        Intent intent = new Intent(getActivity(), FilmeActivity.class);
        intent.putExtra(FilmeActivity.FILME_PARAMETER, filme);

        startActivity(intent);
    }
}
