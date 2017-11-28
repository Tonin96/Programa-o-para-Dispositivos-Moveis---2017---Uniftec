package br.com.uniftec.ecommercemobile.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Map;

import br.com.uniftec.ecommercemobile.MainActivity;
import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.Login;
import br.com.uniftec.ecommercemobile.model.UsuarioResponse;
import br.com.uniftec.ecommercemobile.services.LoadingService;
import br.com.uniftec.ecommercemobile.task.CarregarUsuarioTask;
import br.com.uniftec.ecommercemobile.task.LoginUsuarioTask;

public class LoginActivity extends AppCompatActivity
        implements
        View.OnClickListener,
        LoginUsuarioTask.LoginUsuarioDelegate,
        CarregarUsuarioTask.CarregarUsuarioDelegate,
        LoadingService {

    private ProgressDialog progressDialog;

    private Button buttonEntrar;
    private Button buttonNovaConta;
    private EditText email;
    private EditText senha;
    private SharedPreferences user_preferences;

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonEntrar = (Button) findViewById(R.id.activity_login_button_entrar);
        buttonEntrar.setOnClickListener(this);
        buttonNovaConta = (Button) findViewById(R.id.activity_login_button_criar_nova_conta);
        buttonNovaConta.setOnClickListener(this);

        email = (EditText) findViewById(R.id.activity_login_edit_text_email);
        senha = (EditText) findViewById(R.id.activity_login_edit_text_senha);

        user_preferences = this.getSharedPreferences("usuario_preferences", Context.MODE_PRIVATE);
        user_preferences.edit().clear().commit();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.activity_login_button_entrar) {

            boolean validacao = this.validarCamposObrigatorios();

            if(validacao) {
                this.progressDialog(this, "Autenticando");

                Login login = new Login();
                login.setEmail(email.getText().toString());
                login.setSenha(senha.getText().toString());

                LoginUsuarioTask loginUsuarioTask = new LoginUsuarioTask(this);
                loginUsuarioTask.execute(login);
            }
        } else {
            Intent intent = null;
            intent = new Intent(this, CriaContaUsuarioActivity.class);

            this.startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
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

        if(!email.getText().toString().matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$")) {
            email.setError("Email inválido");
            validacao = false;
        }

        if(senha.getText().toString().equals("")) {
            senha.setError("Senha inválida");
            validacao = false;
        }

        return validacao;
    }

    @Override
    public void loginUsuarioSucesso(String token) {
        CarregarUsuarioTask carregarUsuarioTask = new CarregarUsuarioTask(this);
        carregarUsuarioTask.execute(token);

        putStringSharedPreference("X-Token", token);
    }

    @Override
    public void loginUsuarioFalha(String mensagem) {
        dismisProgressDialog();
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sucessoCarregarUsuario(UsuarioResponse usuarioResponse) {
        Gson gson = new Gson();
        String json = gson.toJson(usuarioResponse);

        putStringSharedPreference("usuario", json);

        dismisProgressDialog();
        Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();

        Intent intent = null;
        intent =  new Intent(this, MainActivity.class);

        this.startActivity(intent);
    }

    @Override
    public void falhaCarregarUsuario(String mensagem) {
        dismisProgressDialog();
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    private void putStringSharedPreference(String key, String value) {
        SharedPreferences.Editor editor = user_preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
