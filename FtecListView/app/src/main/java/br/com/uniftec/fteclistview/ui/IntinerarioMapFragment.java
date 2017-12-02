package br.com.uniftec.fteclistview.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.uniftec.fteclistview.R;
import br.com.uniftec.fteclistview.task.CarregarIntinenariosTask;

public class IntinerarioMapFragment extends MapFragment implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter, CarregarIntinenariosTask.CarregarIntinenariosDelegate {

    private GoogleMap googleMap;

    @Override
    public void onResume() {
        super.onResume();

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setInfoWindowAdapter(this);

        // coordenadas do Uniftec
        LatLng coordenada = new LatLng(-29.17394, -51.21894);

        new CarregarIntinenariosTask(this).execute("5485");
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.conteudo_marcador, null, false);

        return view;
    }

    @Override
    public void sucesso(List<LatLng> coordenadas) {

        for (LatLng coordenada : coordenadas) {
            MarkerOptions options = new MarkerOptions().position(coordenada);
            googleMap.addMarker(options);
        }

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadas.get(0), 13));
    }

    @Override
    public void falha(String mensagem) {
        Toast.makeText(getActivity(), mensagem, Toast.LENGTH_SHORT).show();
    }
}
