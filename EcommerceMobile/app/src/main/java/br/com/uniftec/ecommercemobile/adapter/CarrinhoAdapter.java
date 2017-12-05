package br.com.uniftec.ecommercemobile.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.Produto;
import br.com.uniftec.ecommercemobile.services.CarrinhoService;
import br.com.uniftec.ecommercemobile.task.RemoverUsuarioEnderecoTask;
import br.com.uniftec.ecommercemobile.ui.ProdutoActivity;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.ViewHolder>{
    private List<Produto> produtos;
    private CarrinhoService carrinhoService = null;
    private Context context;
    private int posicaoSelecionada;

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
        public void onClick(View view) {
            if(view.getId() == R.id.row_carrinho_retirar) {

                posicaoSelecionada = getAdapterPosition();

                showDialog(view, posicaoSelecionada);
            } else {
                final Intent intent;
                intent =  new Intent(view.getContext(), ProdutoActivity.class);
                intent.putExtra(ProdutoActivity.PRODUTO_PARAMETER, produto);

                view.getContext().startActivity(intent);
            }
        }
    }

    public void add(int position, Produto item) {
        produtos.add(position, item);
        notifyItemInserted(position);
        carrinhoService.adicionarProduto(item);
    }

    private void remove(int position) {
        carrinhoService.removeProduto(produtos, produtos.get(position));
        produtos = carrinhoService.buscaProdutos();
        notifyItemRemoved(position);
        notifyDataSetChanged();
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
        holder.titulo.setText(produtos.get(position).getNome());
        holder.preco.setText(produtos.get(position).getPreco().toString());
        Picasso.with(holder.layout.getContext()).load(holder.produto.getImagemPrincipal().getUrl()).into(holder.imagem);
    }

    @Override
    public int getItemCount() {
        if(produtos != null) {
            return produtos.size();
        }else{
            return 0;
        }
    }

    private void showDialog(final View view, final int position) {

        context = view.getContext();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());

        alertDialog.setTitle("Confirmar exclusão do produto?");

        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,
                                int which) {
                remove(position);
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
}