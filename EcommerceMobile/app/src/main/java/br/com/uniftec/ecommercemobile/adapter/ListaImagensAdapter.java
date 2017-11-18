package br.com.uniftec.ecommercemobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.ProdutoImagem;

public class ListaImagensAdapter extends RecyclerView.Adapter<ListaImagensAdapter.ViewHolder>{
    private List<ProdutoImagem> produtoImagems;

    public ListaImagensAdapter(List<ProdutoImagem> produtoImagems) {
        this.produtoImagems = produtoImagems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagem;
        List<ProdutoImagem> produtoImagemList;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            produtoImagemList = produtoImagems;
            imagem = (ImageView) v.findViewById(R.id.row_lista_imagem_imagem);
        }
    }

    @Override
    public ListaImagensAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_lista_imagens, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(holder.layout.getContext()).load(holder.produtoImagemList.get(position).getUrl()).into(holder.imagem);

    }

    @Override
    public int getItemCount() {
        return produtoImagems.size();
    }
}