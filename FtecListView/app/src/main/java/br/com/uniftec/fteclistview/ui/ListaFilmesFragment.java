package br.com.uniftec.fteclistview.ui;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.uniftec.fteclistview.R;
import br.com.uniftec.fteclistview.adapter.FilmeAdapter;
import br.com.uniftec.fteclistview.model.Filme;
import br.com.uniftec.fteclistview.model.PopularResponse;
import br.com.uniftec.fteclistview.model.Usuario;
import br.com.uniftec.fteclistview.task.CarregarPopularesTask;
import br.com.uniftec.fteclistview.task.IncluirUsuarioTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFilmesFragment extends Fragment implements AdapterView.OnItemClickListener, CarregarPopularesTask.CarregarPopularesDelegate, IncluirUsuarioTask.IncluirUsuarioDelegate {

    private ListView listViewFilmes;
    private FilmeAdapter adapter;
    private List<Filme> dataSource;
    private ProgressDialog progressDialog;

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

        //List<Filme> filmes = DataSource.carregarFilmes(getActivity());
        dataSource = new ArrayList<>();

        adapter = new FilmeAdapter(getActivity(), 0, dataSource);
        listViewFilmes.setAdapter(adapter);

        progressDialog = ProgressDialog.show(getActivity(), "Aguarde", "Carregando filmes", true, false);

        CarregarPopularesTask task = new CarregarPopularesTask(this);

        task.execute("8cc65cc237509b082427cce84df4fe28");

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Filme filme = adapter.getItem(position);

        Intent intent = new Intent(getActivity(), FilmeActivity.class);
        intent.putExtra(FilmeActivity.FILME_PARAMETER, filme);

        startActivity(intent);
    }

    @Override
    public void sucesso(PopularResponse popularResponse) {

        dataSource.clear();
        dataSource.addAll(popularResponse.getFilmes());

        adapter.notifyDataSetChanged();

        Usuario usuario = new Usuario();
        usuario.setCpf("03686644061");
        usuario.setEmail("bruno.marsilio@gmail.com");
        usuario.setNome("Bruno Marsilio");
        usuario.setSenha("123456");
        usuario.setTelefone("54999671473");

        IncluirUsuarioTask incluirUsuarioTask = new IncluirUsuarioTask(this);
        incluirUsuarioTask.execute(usuario);
    }

    @Override
    public void falha(String mensagem) {
        dismisProgressDialog();

        Toast.makeText(getActivity(), mensagem, Toast.LENGTH_LONG).show();
    }

    public void dismisProgressDialog() {
        progressDialog.dismiss();
        progressDialog = null;
    }

    @Override
    public void incluirUsuarioSucesso(String token) {

        Toast.makeText(getActivity(), "Usuário incluído com sucesso " + token, Toast.LENGTH_SHORT).show();

        dismisProgressDialog();
    }

    @Override
    public void incluirUsuarioFalha(String mensagem) {
        Toast.makeText(getActivity(),mensagem, Toast.LENGTH_SHORT).show();
    }
}
