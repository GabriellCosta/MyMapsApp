package com.gabriellcosta.mymapsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

  private static final String TAG = "MainActivity";
  private MarkerOptions marker;
  private double lat;
  private double lng;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.fragment_main_map);

    mapFragment.getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    Log.v(TAG , "Map Ready to be used");
    initMark();
    googleMap.addMarker(marker);
  }

  private void initMark() {
    marker = MarkerFactory.createSimpleMarker(lat, lng);
  }
}
