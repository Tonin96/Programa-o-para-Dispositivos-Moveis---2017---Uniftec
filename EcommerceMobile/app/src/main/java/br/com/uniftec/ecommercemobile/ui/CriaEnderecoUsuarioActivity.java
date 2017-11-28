package br.com.uniftec.ecommercemobile.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.UsuarioEndereco;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import br.com.uniftec.ecommercemobile.services.LoadingService;
import br.com.uniftec.ecommercemobile.task.SalvarUsuarioEnderecoTask;

public class CriaEnderecoUsuarioActivity extends AbstractActivity
        implements
        View.OnClickListener,
        SalvarUsuarioEnderecoTask.SalvarUsuarioEnderecoDelegate,
        LoadingService
{
    private ProgressDialog progressDialog;
    private SharedPreferences user_preferences;
    private Button botaoSalvar;
    private EditText rua;
    private EditText numero;
    private EditText complemento;
    private EditText bairro;
    private EditText cidade;
    private EditText estado;
    private String token;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_cria_endereco_usuario;
    }

    @Override
    protected void setupView() {
        getSupportActionBar().setTitle("Novo Endereço");

        botaoSalvar = (Button) findViewById(R.id.activity_cria_endereco_usuario_button_salvar);
        botaoSalvar.setOnClickListener(this);

        rua = (EditText) findViewById(R.id.activity_cria_endereco_usuario_edit_text_rua);
        numero = (EditText) findViewById(R.id.activity_cria_endereco_usuario_edit_text_numero);
        complemento = (EditText) findViewById(R.id.activity_cria_endereco_usuario_edit_text_complemento);
        bairro = (EditText) findViewById(R.id.activity_cria_endereco_usuario_edit_text_bairro);
        cidade = (EditText) findViewById(R.id.activity_cria_endereco_usuario_edit_text_cidade);
        estado = (EditText) findViewById(R.id.activity_cria_endereco_usuario_edit_text_estado);

        user_preferences = this.getSharedPreferences("usuario_preferences", Context.MODE_PRIVATE);

        token = user_preferences.getString("X-Token", "null");
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.activity_cria_endereco_usuario_button_salvar) {

            boolean validacao = this.validarCamposObrigatorios();

            if(validacao) {

                this.progressDialog(this, "Criando endereço");

                UsuarioEndereco usuarioEndereco = new UsuarioEndereco();
                usuarioEndereco.setLogradouro(rua.getText().toString());
                usuarioEndereco.setNumero(numero.getText().toString());
                usuarioEndereco.setComplemento(complemento.getText().toString());
                usuarioEndereco.setBairro(bairro.getText().toString());
                usuarioEndereco.setCidade(cidade.getText().toString());
                usuarioEndereco.setEstado(estado.getText().toString());

                Object[] parametros = new Object[2];
                parametros[0] = token;
                parametros[1] = usuarioEndereco;

                SalvarUsuarioEnderecoTask salvarUsuarioEnderecoTask = new SalvarUsuarioEnderecoTask(this);
                salvarUsuarioEnderecoTask.execute(parametros);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean validarCamposObrigatorios() {
        boolean validacao = true;

        if(rua.getText().toString().equals("")) {
            rua.setError("Rua inválida");
            validacao = false;
        }

        if(numero.getText().toString().equals("")) {
            numero.setError("Número inválido");
            validacao = false;
        }

        if(complemento.getText().toString().equals("")) {
            complemento.setError("Complemento inválido");
            validacao = false;
        }

        if(bairro.getText().toString().equals("")) {
            bairro.setError("Bairro inválido");
            validacao = false;
        }

        if(cidade.getText().toString().equals("")) {
            cidade.setError("Cidade inválida");
            validacao = false;
        }

        if(estado.getText().toString().equals("")) {
            estado.setError("Estado inválido");
            validacao = false;
        }

        return validacao;
    }

    @Override
    public void salvarUsuarioEnderecoSucesso(UsuarioResponse usuarioResponse) {
        Gson gson = new Gson();
        String json = gson.toJson(usuarioResponse);

        putStringSharedPreference("usuario", json);

        dismisProgressDialog();
        Toast.makeText(this, "Endereço incluído com sucesso", Toast.LENGTH_SHORT).show();

        Intent intent = null;
        intent =  new Intent(this, ListaEnderecosUsuarioActivity.class);

        this.startActivity(intent);
    }

    @Override
    public void salvarUsuarioEnderecoFalha(String mensagem) {
        dismisProgressDialog();
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void progressDialog(Activity activity, String mensagem) {
        progressDialog = ProgressDialog.show(activity, "Aguarde", mensagem, true, false);
    }

    @Override
    public void dismisProgressDialog() {
        progressDialog.dismiss();
        progressDialog = null;
    }

    private void putStringSharedPreference(String key, String value) {
        SharedPreferences.Editor editor = user_preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}