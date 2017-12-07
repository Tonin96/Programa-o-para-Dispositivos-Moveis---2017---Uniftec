package br.com.uniftec.ecommercemobile.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.adapter.ListaEnderecoAdapter;
import br.com.uniftec.ecommercemobile.model.Pedido;
import br.com.uniftec.ecommercemobile.model.PedidoProdutoResponse;
import br.com.uniftec.ecommercemobile.model.PedidoResponse;
import br.com.uniftec.ecommercemobile.model.Produto;
import br.com.uniftec.ecommercemobile.model.UsuarioEndereco;
import br.com.uniftec.ecommercemobile.model.UsuarioEnderecoResponse;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import br.com.uniftec.ecommercemobile.services.CarrinhoService;
import br.com.uniftec.ecommercemobile.task.SalvarPedidoTask;

public class FinalizaCarrinhoActivity extends AbstractActivity  implements SalvarPedidoTask.SalvarPedidoDelegate, View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences preferences;
    private EditText numeroCartao;
    private EditText vencimentoCartao;
    private EditText cvvCartao;
    private Button finalizarCarrinho;
    private CarrinhoService carrinhoService;
    private String token;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_finaliza_carrinho;
    }

    @Override
    protected void setupView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_lista_enderecos_finaliza_carrinho);
        numeroCartao = (EditText) findViewById(R.id.activity_finaliza_carrinho_edit_text_numero_do_cartao);
        vencimentoCartao = (EditText) findViewById(R.id.activity_finaliza_carrinho_edit_text_data_de_vencimento_do_cartao);
        cvvCartao = (EditText) findViewById(R.id.activity_finaliza_carrinho_edit_text_cvv_do_cartao);
        finalizarCarrinho = (Button) findViewById(R.id.activity_finaliza_carrinho_button_finalizar);
        carrinhoService = new CarrinhoService(this);


        preferences = this.getSharedPreferences("usuario_preferences", Context.MODE_PRIVATE);
        token = preferences.getString("X-Token", "null");

        Gson gson = new Gson();
        String usuario = preferences.getString("usuario", "null");
        if (!usuario.equals("null")) {
            retornoJsonUsuarioResponse = gson.fromJson(usuario, UsuarioResponse.class);
        }

        ArrayList<UsuarioEndereco> usuarioEnderecoArrayList = new ArrayList<UsuarioEndereco>();
        for (UsuarioEnderecoResponse usuarioEnderecoResponse : retornoJsonUsuarioResponse.getEnderecos()) {
            usuarioEnderecoArrayList.add(usuarioEnderecoResponse.getEndereco());
        }


        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ListaEnderecoAdapter(usuarioEnderecoArrayList, this);
        recyclerView.setAdapter(mAdapter);

        finalizarCarrinho.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.activity_finaliza_carrinho_button_finalizar){
            Pedido pedido = new Pedido();


            ArrayList<PedidoProdutoResponse> pedidoProdutos = new ArrayList<PedidoProdutoResponse>();
            ArrayList<Produto> produtosCarrinho = carrinhoService.buscaProdutos();

            ArrayList<Long> arrayProdutosId = new ArrayList<>();
            for (Produto produto : produtosCarrinho){
                Log.d("produtoID", String.valueOf(produto.getId()));
                arrayProdutosId.add(produto.getId());
            }

            Integer[] teste  = new Integer[1];
            teste[0] = 230;
            pedido.setProdutos(arrayProdutosId);
            pedido.setCartaoCredito(String.valueOf(numeroCartao.getText()));
            pedido.setCodigoSeguranca(String.valueOf(cvvCartao.getText()));
            pedido.setValidadeCartaoCredito(String.valueOf(vencimentoCartao.getText()));

            Gson gson = new Gson();
            String usuario = preferences.getString("usuario", "null");
            if (!usuario.equals("null")) {
                retornoJsonUsuarioResponse = gson.fromJson(usuario, UsuarioResponse.class);
            }

            Log.d("teste", gson.toJson(pedido));

            for (UsuarioEnderecoResponse usuarioEnderecoResponse : retornoJsonUsuarioResponse.getEnderecos()) {
                pedido.setIdEnderecoUsuario(usuarioEnderecoResponse.getId());
            }
            Log.d("token", token);
            SalvarPedidoTask salvarPedidoTask = new SalvarPedidoTask(this);

            Object[] parametros = new Object[2];
            parametros[0] = token;
            parametros[1] = pedido;

            salvarPedidoTask.execute(parametros);
        }
    }

    @Override
    public void salvarPedidoSucesso(PedidoResponse pedidoResponse) {
        Log.d("Sucesso", "sucesso");
    }

    @Override
    public void salvarPedidoFalha(String mensagem) {
        Log.d("Falha", mensagem);
    }
}
