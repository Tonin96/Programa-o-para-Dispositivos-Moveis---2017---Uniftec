package br.com.uniftec.ecommercemobile.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.EnderecoUsuario;
import br.com.uniftec.ecommercemobile.ui.AlteraEnderecoUsuarioActivity;

/**
 * Created by bruno on 28/10/17.
 */

public class ListaEnderecoAdapter extends RecyclerView.Adapter<ListaEnderecoAdapter.ViewHolder>{
    private List<EnderecoUsuario> enderecos;

    public ListaEnderecoAdapter(ArrayList<EnderecoUsuario> enderecos) {
        this.enderecos = enderecos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public EnderecoUsuario endereco;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            final Intent intent;

            intent =  new Intent(v.getContext(), AlteraEnderecoUsuarioActivity.class);

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

    }

    @Override
    public int getItemCount() {
        return enderecos.size();
    }
}