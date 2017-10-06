package br.com.uniftec.fteclistview.ui;

import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.uniftec.fteclistview.R;
import br.com.uniftec.fteclistview.model.Filme;

public class FilmeActivity extends AbstractActivity implements View.OnClickListener{

    public static final String FILME_PARAMETER = "FILME_PARAMETER";
    private Button fecharButton;
    private Filme filme;
    private TextView tituloTextView;
    private TextView notaTextView;
    private TextView resumoTextView;
    private ImageView imagemImageView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_filme;
    }

    @Override
    protected void setupView() {
        tituloTextView = (TextView)findViewById(R.id.activity_filme_titulo);
        notaTextView = (TextView)findViewById(R.id.activity_filme_nota);
        resumoTextView = (TextView)findViewById(R.id.activity_filme_resumo);
        imagemImageView = (ImageView)findViewById(R.id.activity_filme_imagem);
        fecharButton = (Button)findViewById(R.id.activity_filme_fechar_button);
        fecharButton.setOnClickListener(this);

        filme = (Filme)getIntent().getSerializableExtra(FILME_PARAMETER);

        tituloTextView.setText(filme.getTitulo());
        notaTextView.setText(Double.toString(filme.getNota()));
        resumoTextView.setText(filme.getResumo());

        int idImagem = getResources().getIdentifier(filme.getImagem(), "drawable", getPackageName());
        imagemImageView.setImageDrawable(getDrawable(idImagem));
    }

    @Override
    public void onClick(View view) {

        if(view == fecharButton) {
            finish();
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
}
