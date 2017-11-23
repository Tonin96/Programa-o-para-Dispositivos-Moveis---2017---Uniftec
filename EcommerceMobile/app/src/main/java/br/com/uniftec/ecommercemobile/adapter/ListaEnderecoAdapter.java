package br.com.uniftec.ecommercemobile.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.UsuarioEndereco;

/**
 * Created by bruno on 28/10/17.
 */

public class ListaEnderecoAdapter extends RecyclerView.Adapter<ListaEnderecoAdapter.ViewHolder>{
    private List<UsuarioEndereco> enderecos;

    public ListaEnderecoAdapter(List<UsuarioEndereco> enderecos) {
        this.enderecos = enderecos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public UsuarioEndereco endereco;
        public View layout;
        public TextView logradouro;
        public TextView cidade;
        public TextView estado;
        public Button excluir;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            logradouro = (TextView) v.findViewById(R.id.row_endereco_logradouro);
            cidade = (TextView) v.findViewById(R.id.row_endereco_cidade);
            estado = (TextView) v.findViewById(R.id.row_endereco_estado);
            excluir = (Button) v.findViewById(R.id.row_endereco_excluir);
            excluir.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {


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
    public ListaEnderecoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_lista_endereco, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.endereco = enderecos.get(position);
        holder.logradouro.setText(enderecos.get(position).toString());
        holder.cidade.setText(enderecos.get(position).getCidade());
        holder.estado.setText(enderecos.get(position).getEstado());

    }

    @Override
    public int getItemCount() {
        return enderecos.size();
    }
}