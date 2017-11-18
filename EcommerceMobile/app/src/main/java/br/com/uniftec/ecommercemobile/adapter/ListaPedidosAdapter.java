package br.com.uniftec.ecommercemobile.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.Pedido;
import br.com.uniftec.ecommercemobile.model.PedidoProduto;
import br.com.uniftec.ecommercemobile.model.Produto;
import br.com.uniftec.ecommercemobile.ui.PedidosActivity;
import br.com.uniftec.ecommercemobile.ui.ProdutoActivity;

/**
 * Created by bruno on 28/10/17.
 */

public class ListaPedidosAdapter extends RecyclerView.Adapter<ListaPedidosAdapter.ViewHolder> {
    private List<Pedido> pedidos;

    public ListaPedidosAdapter(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Pedido pedido;
        public View layout;
        public TextView dataPedido;
        public TextView status;
        public TextView qtd;
        public TextView precoTotal;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            dataPedido = v.findViewById(R.id.row_pedido_data_pedido);
            status = v.findViewById(R.id.row_pedido_status);
            qtd = v.findViewById(R.id.row_lista_pedido_qtd);
            precoTotal = v.findViewById(R.id.row_lista_pedido_total);
            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            final Intent intent;

            intent =  new Intent(v.getContext(), PedidosActivity.class);
            intent.putExtra(PedidosActivity.PEDIDO_PARAMETER, pedido);

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
    public ListaPedidosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_lista_pedido, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.pedido = pedidos.get(position);
        holder.dataPedido.setText(pedidos.get(position).getData());
        holder.status.setText(pedidos.get(position).getStatus());
        holder.qtd.setText(String.valueOf(pedidos.get(position).getItens().size()));

        double total = 0;
        List<PedidoProduto> produtos = pedidos.get(position).getItens();

        for (PedidoProduto produto:produtos) {
            total += produto.getValor();
        }

        holder.precoTotal.setText(String.valueOf(total));
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }
}