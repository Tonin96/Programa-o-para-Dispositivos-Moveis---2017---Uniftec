package br.com.uniftec.ecommercemobile.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.Produto;
import br.com.uniftec.ecommercemobile.ui.ProdutoActivity;

/**
 * Created by bruno on 28/10/17.
 */

public class ListaProdutoAdapter extends RecyclerView.Adapter<ListaProdutoAdapter.ViewHolder>{
    private List<Produto> produtos;

    public ListaProdutoAdapter(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titulo;
        public TextView preco;
        public ImageView imagem;
        public Produto produto;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            titulo = (TextView) v.findViewById(R.id.row_lista_produto_titulo);
            preco = (TextView) v.findViewById(R.id.row_lista_produto_preco);
            imagem = (ImageView) v.findViewById(R.id.row_lista_produto_imagem_principal);
            v.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {

            final Intent intent;

            intent =  new Intent(v.getContext(), ProdutoActivity.class);
            intent.putExtra(ProdutoActivity.PRODUTO_PARAMETER, produto);


            v.getContext().startActivity(intent);
        }
    }

    /*public void add(int position, Produto item) {
        produtos.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        produtos.remove(position);
        notifyItemRemoved(position);
    }*/


    @Override
    public ListaProdutoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_lista_produto, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.produto = produtos.get(position);
        holder.titulo.setText(produtos.get(position).getTitulo());
        holder.preco.setText(produtos.get(position).getPreco().toString());

    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }
}