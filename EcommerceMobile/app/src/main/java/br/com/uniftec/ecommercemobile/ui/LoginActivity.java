package br.com.uniftec.ecommercemobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.uniftec.ecommercemobile.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonEntrar;
    private Button buttonNovaConta;

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonEntrar = (Button) findViewById(R.id.activity_login_button_salvar);
        buttonNovaConta = (Button) findViewById(R.id.activity_login_button_criar_nova_conta);
        buttonEntrar.setOnClickListener(this);
        buttonNovaConta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.activity_login_button_salvar) {



            finish();
        } else {
            Intent intent = null;
            intent =  new Intent(this, CriaContaUsuarioActivity.class);

            this.startActivity(intent);
        }

    }
}
