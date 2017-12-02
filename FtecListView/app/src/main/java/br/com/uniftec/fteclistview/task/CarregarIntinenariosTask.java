package br.com.uniftec.fteclistview.task;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.uniftec.fteclistview.model.PopularResponse;
import br.com.uniftec.fteclistview.service.MovieService;
import br.com.uniftec.fteclistview.service.PoaService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class CarregarIntinenariosTask extends AsyncTask<String, Void, List<LatLng>>{

    private CarregarIntinenariosDelegate delegate;
    private PoaService poaservice;

    public CarregarIntinenariosTask(CarregarIntinenariosDelegate delegate) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.poatransporte.com.br")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        poaservice = retrofit.create(PoaService.class);

        this.delegate = delegate;
    }

    @Override
    protected List<LatLng> doInBackground(String... parameters) {

        Call<JsonNode> call = poaservice.carregarIntinenarios("il", parameters[0]);

        try {
            JsonNode response = call.execute().body();
            Iterator<Map.Entry<String, JsonNode>> properties = response.fields();

            List<LatLng> coordenadas = new ArrayList<>();

            while (properties.hasNext()) {
                Map.Entry<String, JsonNode> propertie = properties.next();

                try {
                    Integer key = Integer.parseInt(propertie.getKey());
                    JsonNode value = propertie.getValue();

                    double latitude = value.get("lat").asDouble();
                    double longitude = value.get("lng").asDouble();

                    LatLng coordenada = new LatLng(latitude, longitude);

                    coordenadas.add(coordenada);

                } catch (NumberFormatException e) {
                    continue;
                }
            }

            return coordenadas;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<LatLng> coordenadas) {
        if(coordenadas != null) {
            delegate.sucesso(coordenadas);
        } else {
            delegate.falha("Não carregou as coordenadas");
        }
    }

    public interface CarregarIntinenariosDelegate {
        public void sucesso(List<LatLng> coordenadas);
        public void falha(String mensagem);
    }
}
