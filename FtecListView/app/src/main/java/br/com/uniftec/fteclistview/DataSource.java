package br.com.uniftec.fteclistview;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.uniftec.fteclistview.model.Filme;

/**
 * Created by marioklein on 21/09/17.
 */

public class DataSource {

    public static List<Filme> carregarFilmes(Context context) {

        List<Filme> filmes = new ArrayList<>();

        try {
            JSONArray results = new JSONArray(loadJSONFromAsset(context));


            for (int i = 0; i < results.length(); i++) {
                JSONObject filmeResult = results.getJSONObject(i);

                String titulo = filmeResult.getString("original_title");
                String resumo = filmeResult.getString("overview");
                String lancamento = filmeResult.getString("release_date");
                String imagem = filmeResult.getString("poster_path").toLowerCase();
                int votos = filmeResult.getInt("vote_count");
                double nota = filmeResult.getDouble("vote_average");

                Filme filme = new Filme();
                filme.setTitulo(titulo);
                filme.setResumo(resumo);
                filme.setImagem("ft_" + imagem.substring(1, imagem.length() - 4));
                filme.setLancamento(lancamento);
                filme.setVotos(votos);
                filme.setNota(nota);

                filmes.add(filme);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filmes;
    }

    public static String loadJSONFromAsset(Context context) throws IOException {
        String json = null;
        try {
            InputStream is = context.getResources().openRawResource(R.raw.movie);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
