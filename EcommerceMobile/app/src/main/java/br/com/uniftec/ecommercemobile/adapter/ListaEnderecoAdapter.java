package br.com.uniftec.ecommercemobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.UsuarioEndereco;

public class ListaEnderecoAdapter extends RecyclerView.Adapter<ListaEnderecoAdapter.ViewHolder>{

    private List<UsuarioEndereco> enderecos;

    public ListaEnderecoAdapter(List<UsuarioEndereco> enderecos) {
        this.enderecos = enderecos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public View layout;
        public TextView bairro;
        public TextView logradouro;
        public TextView complemento;
        public TextView numero;
        public TextView cidade;
        public TextView estado;
        public Button excluir;

        public ViewHolder(View view) {
            super(view);
            layout = view;

            bairro = (TextView) view.findViewById(R.id.row_lista_endereco_bairro);
            logradouro = (TextView) view.findViewById(R.id.row_lista_endereco_logradouro);
            complemento = (TextView) view.findViewById(R.id.row_lista_endereco_complemento);
            numero = (TextView) view.findViewById(R.id.row_lista_endereco_numero);
            cidade = (TextView) view.findViewById(R.id.row_lista_endereco_cidade);
            estado = (TextView) view.findViewById(R.id.row_lista_endereco_estado);

            excluir = (Button) view.findViewById(R.id.row_lista_endereco_excluir);
            excluir.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


        }
    }

    @Override
    public ListaEnderecoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_lista_endereco, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bairro.setText(enderecos.get(position).getBairro());
        holder.logradouro.setText(enderecos.get(position).getLogradouro());
        holder.complemento.setText(enderecos.get(position).getComplemento());
        holder.numero.setText(enderecos.get(position).getNumero());
        holder.cidade.setText(enderecos.get(position).getCidade());
        holder.estado.setText(enderecos.get(position).getEstado());
    }

    @Override
    public int getItemCount() {
        return enderecos.size();
    }
}