package br.com.uniftec.fteclistview.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.uniftec.fteclistview.R;
import br.com.uniftec.fteclistview.model.Filme;

/**
 * Created by brunomarsilio on 21/09/17.
 */

public class FilmeAdapter extends ArrayAdapter<Filme>{

    private LayoutInflater layoutInflater;

    public FilmeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Filme> objects) {
        super(context, resource, objects);

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

        Filme filme = getItem(position);

        textView.setText(filme.getTitulo());

        int idImagem = convertView.getResources().getIdentifier(filme.getImagem(), "drawable", getContext().getPackageName());

        try {
            Log.d("ADAP", filme.getImagem());
            imagem.setImageDrawable(getContext().getDrawable(idImagem));
        } catch (OutOfMemoryError e) {
            Log.d("ADAP", filme.getImagem());
            imagem.setImageDrawable(null);
        }

        return convertView;
    }
}
