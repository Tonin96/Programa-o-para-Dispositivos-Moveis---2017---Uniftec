package br.com.uniftec.ecommercemobile.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.Usuario;
import br.com.uniftec.ecommercemobile.services.LoadingService;
import br.com.uniftec.ecommercemobile.task.SalvarUsuarioTask;

public class CriaContaUsuarioActivity extends AppCompatActivity implements View.OnClickListener, SalvarUsuarioTask.IncluirUsuarioDelegate, LoadingService{

    private ProgressDialog progressDialog;
    private Button botaoSalvar;
    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText cpf;
    private EditText telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_conta_usuario);

        botaoSalvar = (Button) findViewById(R.id.activity_cria_conta_usuario_button_salvar);
        botaoSalvar.setOnClickListener(this);

        nome = (EditText) findViewById(R.id.activity_cria_conta_usuario_edit_text_nome);
        email = (EditText) findViewById(R.id.activity_cria_conta_usuario_edit_text_email);
        senha = (EditText) findViewById(R.id.activity_cria_conta_usuario_edit_text_senha);
        cpf = (EditText) findViewById(R.id.activity_cria_conta_usuario_edit_text_cpf);
        telefone = (EditText) findViewById(R.id.activity_cria_conta_usuario_edit_text_telefone);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.activity_cria_conta_usuario_button_salvar) {

            boolean validacao = this.validarCamposObrigatorios();

            if(validacao) {

                this.progressDialog(this, "Criando usuário");

                Usuario usuario = new Usuario();
                usuario.setCpf(cpf.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setNome(nome.getText().toString());
                usuario.setSenha(senha.getText().toString());
                usuario.setTelefone(telefone.getText().toString());

                SalvarUsuarioTask salvarUsuarioTask = new SalvarUsuarioTask(this);
                salvarUsuarioTask.execute(usuario);
            }

        }

    }

    @Override
    public void incluirUsuarioSucesso(String token) {
        dismisProgressDialog();
        Toast.makeText(this, "Usuário incluído com sucesso " + token, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void incluirUsuarioFalha(String mensagem) {
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
