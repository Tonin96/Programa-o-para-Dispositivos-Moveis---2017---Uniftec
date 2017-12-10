package br.com.uniftec.ecommercemobile.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.UsuarioEndereco;

public class ListaEnderecoPedidoAdapter extends RecyclerView.Adapter<ListaEnderecoPedidoAdapter.ViewHolder> {

    private List<UsuarioEndereco> enderecos;
    private SharedPreferences preferences;
    private String token;
    private Context context;
    public int mSelectedItem = 0;

    public ListaEnderecoPedidoAdapter(List<UsuarioEndereco> enderecos, Context context) {
        this.enderecos = enderecos;
        this.context = context;

        preferences = this.context.getSharedPreferences("usuario_preferences", Context.MODE_PRIVATE);
        this.token = preferences.getString("X-Token", "null");
    }


    private void putStringSharedPreference(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public View layout;
        public TextView bairro;
        public TextView logradouro;
        public TextView complemento;
        public TextView numero;
        public TextView cidade;
        public TextView estado;
        public RadioButton endereco_selecionado;

        public ViewHolder(View view) {
            super(view);
            layout = view;

            bairro = (TextView) view.findViewById(R.id.row_lista_endereco_pedido_bairro);
            logradouro = (TextView) view.findViewById(R.id.row_lista_endereco_pedido_logradouro);
            complemento = (TextView) view.findViewById(R.id.row_lista_endereco_pedido_complemento);
            numero = (TextView) view.findViewById(R.id.row_lista_endereco_pedido_numero);
            cidade = (TextView) view.findViewById(R.id.row_lista_endereco_pedido_cidade);
            estado = (TextView) view.findViewById(R.id.row_lista_endereco_pedido_estado);
            endereco_selecionado = (RadioButton) view.findViewById(R.id.endereco_selecionado);

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    endereco_selecionado.setTag(enderecos.get(getAdapterPosition()));
                    notifyItemRangeChanged(0, enderecos.size());
                }
            };
            endereco_selecionado.setOnClickListener(clickListener);
        }
    }

    @Override
    public ListaEnderecoPedidoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_lista_endereco_pedido, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)  {
        holder.bairro.setText(enderecos.get(position).getBairro());
        holder.logradouro.setText(enderecos.get(position).getLogradouro());
        holder.complemento.setText(enderecos.get(position).getComplemento());
        holder.numero.setText(enderecos.get(position).getNumero());
        holder.cidade.setText(enderecos.get(position).getCidade());
        holder.estado.setText(enderecos.get(position).getEstado());
        holder.endereco_selecionado.setChecked(position == mSelectedItem);
        holder.endereco_selecionado.setTag(enderecos.get(mSelectedItem));

    }

    @Override
    public int getItemCount() {
        return enderecos.size();
    }
}