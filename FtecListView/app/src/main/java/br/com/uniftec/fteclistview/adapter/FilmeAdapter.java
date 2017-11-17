package br.com.uniftec.fteclistview.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.uniftec.fteclistview.R;
import br.com.uniftec.fteclistview.model.Filme;

public class FilmeAdapter extends ArrayAdapter<Filme> implements View.OnClickListener{

    private LayoutInflater layoutInflater;
    private SharedPreferences preferences;

    public FilmeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Filme> objects) {
        super(context, resource, objects);

        preferences = context.getSharedPreferences("filme_preferences", Context.MODE_PRIVATE);

        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            // linear layout
            convertView = layoutInflater.inflate(R.layout.row_list_views_filmes, parent, false);
        }

        ImageView imagem = (ImageView)convertView.findViewById(R.id.row_list_view_filme_imagem);
        TextView textView = (TextView)convertView.findViewById(R.id.row_list_view_filme_titulo);

        Button favoritar = (Button)convertView.findViewById(R.id.row_list_view_filme_favoritar);

        Filme filme = getItem(position);

        boolean favorito = preferences.getBoolean(filme.getTitulo(), false);
        favoritar.setBackgroundResource(favorito ? R.drawable.botao_favoritar_preenchido : R.drawable.botao_favoritar_vazado);

        textView.setText(filme.getTitulo());

        favoritar.setTag(position);
        favoritar.setOnClickListener(this);

        try {
            String imagemUrl = "http://image.tmdb.org/t/p/w780/" + filme.getImagem();
            Picasso.with(getContext()).load(imagemUrl).into(imagem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.row_list_view_filme_favoritar) {

            Filme filme = getItem((Integer)view.getTag());

            boolean favorito = preferences.getBoolean(filme.getTitulo(), false);
            favorito = !favorito;

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(filme.getTitulo(), favorito);
            editor.commit();

            notifyDataSetChanged();
        }

    }
}
