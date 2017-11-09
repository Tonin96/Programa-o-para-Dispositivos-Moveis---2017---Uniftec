package br.com.uniftec.ecommercemobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.solver.SolverVariable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.uniftec.ecommercemobile.MainActivity;
import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.Produto;
import br.com.uniftec.ecommercemobile.services.CarrinhoService;
import br.com.uniftec.ecommercemobile.ui.ProdutoActivity;

/**
 * Created by bruno on 28/10/17.
 */

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.ViewHolder>{
    private List<Produto> produtos;
    private CarrinhoService carrinhoService = null;

    public CarrinhoAdapter(@NonNull Context context)  {
        this.carrinhoService = new CarrinhoService(context);
        this.produtos = carrinhoService.buscaProdutos();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titulo;
        public TextView preco;
        public ImageView imagem;
        public Button botaoRetirar;
        public Produto produto;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            titulo = (TextView) v.findViewById(R.id.row_carrinho_titulo);
            preco = (TextView) v.findViewById(R.id.row_carrinho_preco);
            imagem = (ImageView) v.findViewById(R.id.row_carrinho_imagem_principal);
            botaoRetirar = (Button) v.findViewById(R.id.row_carrinho_retirar);
            v.setOnClickListener(this);
            botaoRetirar.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.row_carrinho_retirar){
                remove(getAdapterPosition());

            }else{
                final Intent intent;

                intent =  new Intent(v.getContext(), ProdutoActivity.class);
                intent.putExtra(ProdutoActivity.PRODUTO_PARAMETER, produto);

                v.getContext().startActivity(intent);
            }
        }
    }

    public void add(int position, Produto item) {
        produtos.add(position, item);
        notifyItemInserted(position);
        carrinhoService.adicionarProduto(item);
    }

    public void remove(int position) {
        carrinhoService.removeProduto(produtos.get(position));
        produtos.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public CarrinhoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_carrinho, parent, false);
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
        if(produtos != null) {
            return produtos.size();
        }else{
            return 0;
        }
    }
}