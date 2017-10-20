package br.com.uniftec.ecommercemobile.adapter;

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

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.Produto;


/**
 * Created by bruno on 19/10/17.
 */

public class ListaProdutoAdapter extends ArrayAdapter<Produto> implements View.OnClickListener{
    private LayoutInflater layoutInflater;

    public ListaProdutoAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);

        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            // linear layout
            convertView = layoutInflater.inflate(R.layout.row_recycler_view_lista_produto, parent, false);
        }

        ImageView imagem = (ImageView)convertView.findViewById(R.id.row_recycler_view_lista_produto_imagem);
        TextView titulo = (TextView)convertView.findViewById(R.id.row_recycler_view_lista_produto_titulo);
        TextView preco = (TextView)convertView.findViewById(R.id.row_recycler_view_lista_produto_preco);
        TextView precoDesconto = (TextView)convertView.findViewById(R.id.row_recycler_view_lista_produto_preco_desconto);

        Produto produto = getItem(position);

        titulo.setText(produto.getTitulo());
        preco.setText(produto.getPreco().toString());
        precoDesconto.setText(produto.getPreco_desconto().toString());

        int idImagem = convertView.getResources().getIdentifier(produto.getImagem_principal(), "drawable", getContext().getPackageName());

        try {
            Log.d("ADAP", produto.getImagem_principal());
            imagem.setImageDrawable(getContext().getDrawable(idImagem));
        } catch (OutOfMemoryError e) {
            Log.d("ADAP", produto.getImagem_principal());
            imagem.setImageDrawable(null);
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {

    }
}
