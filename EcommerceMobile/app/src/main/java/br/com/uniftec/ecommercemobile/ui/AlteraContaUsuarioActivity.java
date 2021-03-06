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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import br.com.uniftec.ecommercemobile.MainActivity;
import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.Usuario;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import br.com.uniftec.ecommercemobile.task.AtualizarUsuarioTask;

public class AlteraContaUsuarioActivity extends AbstractActivity
        implements
        View.OnClickListener,
        AtualizarUsuarioTask.AtualizarUsuarioDelegate {

    private Button buttonAtualizar;
    private Button buttonEndereco;
    private SharedPreferences preferences;
    private ProgressDialog progressDialog;
    private TextView nomeTitulo;
    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText cpf;
    private EditText telefone;
    private String token;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_altera_conta_usuario;
    }

    @Override
    protected void setupView() {
        getSupportActionBar().setTitle("Minha Conta");

        buttonAtualizar = (Button) findViewById(R.id.activity_altera_conta_usuario_button_atualizar);
        buttonAtualizar.setOnClickListener(this);

        buttonEndereco = (Button) findViewById(R.id.activity_altera_conta_usuario_button_endereco);
        buttonEndereco.setOnClickListener(this);

        nomeTitulo = (TextView) findViewById(R.id.activity_altera_conta_usuario_titulo);
        nome = (EditText) findViewById(R.id.activity_altera_conta_usuario_edit_text_nome);
        email = (EditText) findViewById(R.id.activity_altera_conta_usuario_edit_text_email);
        senha = (EditText) findViewById(R.id.activity_altera_conta_usuario_edit_text_senha);
        cpf = (EditText) findViewById(R.id.activity_altera_conta_usuario_edit_text_cpf);
        telefone = (EditText) findViewById(R.id.activity_altera_conta_usuario_edit_text_telefone);

        preferences = this.getSharedPreferences("usuario_preferences", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String usuario = preferences.getString("usuario", "null");
        if(!usuario.equals("null")) {
            retornoJsonUsuarioResponse = gson.fromJson(usuario, UsuarioResponse.class);

            nomeTitulo.setText(retornoJsonUsuarioResponse.getNome());
            nome.setText(retornoJsonUsuarioResponse.getNome());
            email.setText(retornoJsonUsuarioResponse.getEmail());
            senha.setText(retornoJsonUsuarioResponse.getSenha());
            cpf.setText(retornoJsonUsuarioResponse.getCpf());
            telefone.setText(retornoJsonUsuarioResponse.getTelefone());
        }

        token = preferences.getString("X-Token", "null");
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.activity_altera_conta_usuario_button_atualizar) {

            boolean validacao = this.validarCamposObrigatorios();

            if(validacao) {

                this.progressDialog(this, "Atualizando dados");

                AtualizarUsuarioTask atualizarUsuarioTask = new AtualizarUsuarioTask(this);

                Usuario usuario = new Usuario();
                usuario.setCpf(cpf.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setNome(nome.getText().toString());
                usuario.setSenha(senha.getText().toString());
                usuario.setTelefone(telefone.getText().toString());

                Object[] parametros = new Object[2];
                parametros[0] = token;
                parametros[1] = usuario;

                atualizarUsuarioTask.execute(parametros);
            }
        }

        if(view.getId() == R.id.activity_altera_conta_usuario_button_endereco) {
            final Intent intent =  new Intent(this, ListaEnderecosUsuarioActivity.class);
            this.startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void atualizarUsuarioSucesso(UsuarioResponse usuarioResponse) {
        Gson gson = new Gson();
        String json = gson.toJson(usuarioResponse);

        putStringSharedPreference("usuario", json);

        dismisProgressDialog();
        Toast.makeText(this, "Usuário atualizado com sucesso", Toast.LENGTH_SHORT).show();

        finish();

        Intent intent = null;
        intent =  new Intent(this, MainActivity.class);

        this.startActivity(intent);
    }

    @Override
    public void atualizarUsuarioFalha(String mensagem) {
        dismisProgressDialog();
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    public void progressDialog(Activity activity, String mensagem) {
        progressDialog = ProgressDialog.show(activity, "Aguarde", mensagem, true, false);
    }

    public void dismisProgressDialog() {
        progressDialog.dismiss();
        progressDialog = null;
    }

    private void putStringSharedPreference(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public boolean validarCamposObrigatorios() {
        boolean validacao = true;
        if(!cpf.getText().toString().matches("[0-9]{11}")) {
            cpf.setError("CPF inválido");
            validacao = false;
        }

        if(!email.getText().toString().matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$")) {
            email.setError("Email inválido");
            validacao = false;
        }

        if(nome.getText().toString().equals("")) {
            nome.setError("Nome inválido");
            validacao = false;
        }

        if(senha.getText().toString().equals("")) {
            senha.setError("Senha inválida");
            validacao = false;
        }

        if(telefone.getText().toString().equals("")) {
            telefone.setError("Telefone inválido");
            validacao = false;
        }

        return validacao;
    }
}
