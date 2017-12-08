package br.com.uniftec.ecommercemobile.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class ListaPedidosAdapter extends RecyclerView.Adapter<ListaPedidosAdapter.ViewHolder> {

    private List<Pedido> pedidos;
    private Context context;

    public ListaPedidosAdapter(List<Pedido> pedidos, Context context) {

        this.pedidos = pedidos;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Pedido pedido;
        public View layout;
        TextView dataPedido;
        TextView status;
        TextView qtd;
        TextView precoTotal;

        ViewHolder(View view) {
            super(view);
            layout = view;
            dataPedido = view.findViewById(R.id.row_lista_pedido_data_pedido);
            status = view.findViewById(R.id.row_lista_pedido_status);
            precoTotal = view.findViewById(R.id.row_lista_pedido_total);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            final Intent intent;

            intent = new Intent(view.getContext(), PedidosActivity.class);
            intent.putExtra(PedidosActivity.PEDIDO_PARAMETER, pedido);

            view.getContext().startActivity(intent);
        }
    }

    @Override
    public ListaPedidosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_lista_pedido, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.dataPedido.setText(pedidos.get(position).getData());
        holder.status.setText(pedidos.get(position).getStatus());
        holder.precoTotal.setText(pedidos.get(position).getTotal().toString());

    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }
}