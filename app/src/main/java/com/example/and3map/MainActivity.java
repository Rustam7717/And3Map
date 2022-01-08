package com.example.and3map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.and3map.databinding.ActivityMainBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityMainBinding binding;
    private GoogleMap map;
    private Button btnDraw, btnClean;

    Polyline polyline = null;
    List<LatLng> latLngList = new ArrayList<>();
    List<Marker> markerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        binding.btnDraw.findViewById(R.id.btn_draw);
        binding.btnClean.findViewById(R.id.btn_clean);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        if (polyline != null) polyline.remove();
                        //Create PolylineOptions
                        PolylineOptions polylineOptions = new PolylineOptions()
                                .addAll(latLngList).clickable(true);
                        polyline = map.addPolyline(polylineOptions);

                    }
                });

                btnClean.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (polyline != null) polyline.remove();
                        for (Marker marker : markerList) marker.remove();
                        latLngList.clear();


                    }
                });

            }
        });
    }
}