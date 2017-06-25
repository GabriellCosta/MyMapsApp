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
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocomplete.IntentBuilder;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

  private static final String TAG = "MainActivity";
  private static final String POSITION_KEY = "POSITION_KEY";
  private static final int RC_PLACES_AUTOCOMPLETE = 231;
  private MarkerOptions marker;
  private LatLng position;
  private GoogleMapsManager googleMapsManagerImpl;

  private View imageButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initViews();

    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.fragment_main_map);

    mapFragment.getMapAsync(this);

    imageButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        googleMapsManagerImpl.moveToMarker();
      }
    });

  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelable(POSITION_KEY, googleMapsManagerImpl.getMarker().getPosition());
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    if (savedInstanceState != null
        && savedInstanceState.containsKey(POSITION_KEY)) {
      position = savedInstanceState.getParcelable(POSITION_KEY);
    }
  }

  @Override
  public void onMapReady(final GoogleMap googleMap) {
    Log.v(TAG, "Map Ready to be used");
    initMark();
    googleMapsManagerImpl = new GoogleMapsManagerImpl(googleMap, marker);
    googleMapsManagerImpl.update();

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

    if (resultCode == RESULT_OK && requestCode == RC_PLACES_AUTOCOMPLETE) {
      final Place place = PlaceAutocomplete.getPlace(this, data);
      Log.i(TAG, "Place: " + place.getName());
      googleMapsManagerImpl.update(place);
      setTitle(place.getName());

    }

  }

  private void initMark() {
    final double lat, lng;
    if (position != null) {
      lat = position.latitude;
      lng = position.longitude;
    } else {
      lat = lng = 0.0;
    }
    marker = MarkerFactory.createSimpleMarker(lat, lng);
  }

  private void initViews() {
    imageButton = findViewById(R.id.imb_main_map);
  }
}
