package com.gabriellcosta.mymapsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocomplete.IntentBuilder;
import com.google.android.gms.maps.CameraUpdateFactory;
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
  private static final int RC_PLACES_AUTOCOMPLETE = 231;
  private MarkerOptions marker;
  private double lat;
  private double lng;

  private View imageButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initViews();

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
  public void onMapReady(final GoogleMap googleMap) {
    Log.v(TAG, "Map Ready to be used");
    initMark();
    googleMap.addMarker(marker);
    googleMap.setOnMarkerDragListener(this);
    imageButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        googleMap.animateCamera(CameraUpdateFactory
            .newLatLng(new LatLng(lat, lng)));
      }
    });

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    final boolean result;
    final int itemId = item.getItemId();

    if (itemId == R.id.menu_search) {
      startPlaceAutoComplete();
      result = true;
    } else {
      result = false;
    }

    return result;
  }

  private void startPlaceAutoComplete() {
    try {
      final Intent intentBuilder = new IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
          .build(this);
      startActivityForResult(intentBuilder, RC_PLACES_AUTOCOMPLETE);
    } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
      Log.e(TAG, e.getMessage(), e);
    }

  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);



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

  private void initViews() {
    imageButton = findViewById(R.id.imb_main_map);
  }
}
