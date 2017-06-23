package com.gabriellcosta.mymapsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
    OnMarkerDragListener {

  private static final String TAG = "MainActivity";
  private static final String LAT_KEY = "LAT_KEY";
  private static final String LNG_KEY = "LNG_KEY";
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
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putDouble(LAT_KEY, lat);
    outState.putDouble(LNG_KEY, lng);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    if (savedInstanceState != null
        && savedInstanceState.containsKey(LAT_KEY)
        && savedInstanceState.containsKey(LNG_KEY)) {
      lat = savedInstanceState.getDouble(LAT_KEY);
      lng = savedInstanceState.getDouble(LNG_KEY);
    }
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    Log.v(TAG , "Map Ready to be used");
    initMark();
    googleMap.addMarker(marker);
    googleMap.setOnMarkerDragListener(this);
  }

  private void initMark() {
    marker = MarkerFactory.createSimpleMarker(lat, lng);
  }

  @Override
  public void onMarkerDragStart(Marker marker) {

  }

  @Override
  public void onMarkerDrag(Marker marker) {

  }

  @Override
  public void onMarkerDragEnd(Marker marker) {
    final LatLng position = marker.getPosition();
    lat = position.latitude;
    lng = position.longitude;
  }
}
