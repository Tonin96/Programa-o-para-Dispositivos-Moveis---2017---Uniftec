package br.com.uniftec.ecommercemobile.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.UsuarioEndereco;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import br.com.uniftec.ecommercemobile.task.RemoverUsuarioEnderecoTask;
import br.com.uniftec.ecommercemobile.ui.ListaEnderecosUsuarioActivity;

public class ListaEnderecoAdapter extends RecyclerView.Adapter<ListaEnderecoAdapter.ViewHolder>
        implements
        RemoverUsuarioEnderecoTask.RemoverUsuarioEnderecoDelegate {

    private List<UsuarioEndereco> enderecos;
    private SharedPreferences preferences;
    private String token;
    private ProgressDialog progressDialog;
    private Context context;
    private int posicaoSelecionada;

    public ListaEnderecoAdapter(List<UsuarioEndereco> enderecos, Context context) {
        this.enderecos = enderecos;
        this.context = context;

        preferences = this.context.getSharedPreferences("usuario_preferences", Context.MODE_PRIVATE);
        this.token = preferences.getString("X-Token", "null");
    }

    private void showDialog(final View view, final int position) {

        context = view.getContext();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());

        alertDialog.setTitle("Confirmar exclusão do endereço?");

        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,
                                int which) {
                progressDialog(context, "Removendo endereço");

                Long idSelecionado = enderecos.get(position).getId();

                Object[] parametros = new Object[2];
                parametros[0] = token;
                parametros[1] = idSelecionado;

                RemoverUsuarioEnderecoTask removerUsuarioEnderecoTask = new RemoverUsuarioEnderecoTask(ListaEnderecoAdapter.this);
                removerUsuarioEnderecoTask.execute(parametros);
            }
        });

        alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    @Override
    public void removerUsuarioEnderecoSucesso(UsuarioResponse usuarioResponse) {
        Gson gson = new Gson();
        String json = gson.toJson(usuarioResponse);

        putStringSharedPreference("usuario", json);

        enderecos.remove(posicaoSelecionada);
        notifyItemRemoved(posicaoSelecionada);

        dismisProgressDialog();

        Toast.makeText(this.context, "Endereço removido com sucesso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removerUsuarioEnderecoFalha(String mensagem) {
        dismisProgressDialog();
        Toast.makeText(this.context, mensagem, Toast.LENGTH_SHORT).show();
    }

    private void progressDialog(Context context, String mensagem) {
        progressDialog = ProgressDialog.show(context, "Aguarde", mensagem, true, false);
    }

    private void dismisProgressDialog() {
        progressDialog.dismiss();
        progressDialog = null;
    }

    private void putStringSharedPreference(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
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

            excluir = (Button) view.findViewById(R.id.row_lista_endereco_botao_excluir);
            excluir.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.row_lista_endereco_botao_excluir) {

                posicaoSelecionada = getAdapterPosition();

                showDialog(view, posicaoSelecionada);
            }
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