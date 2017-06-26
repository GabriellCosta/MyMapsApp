package com.gabriellcosta.mymapsapp;

import android.util.Log;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public final class GoogleMapsManagerImpl implements GoogleMapsManager, OnMarkerDragListener {

  private static final String TAG = "GoogleMapsManagerImpl";
  private final GoogleMap googleMap;
  private final MarkerOptions marker;

  public GoogleMapsManagerImpl(GoogleMap googleMap, MarkerOptions marker) {
    Log.i(TAG, "Init GoogleMapsManagerImpl");
    this.googleMap = googleMap;
    this.marker = marker;
    this.googleMap.setOnMarkerDragListener(this);
  }

  @Override
  public void update() {
    googleMap.clear();
    googleMap.addMarker(marker);
    moveToMarker();
    Log.d(TAG, String.format("Marker placed at LAT: %s LONG: %s", marker.getPosition().latitude,
        marker.getPosition().longitude));
  }

  @Override
  public void update(LatLng latLng) {
    marker.position(latLng);
    update();
  }

  @Override
  public void moveToMarker() {
    googleMap.animateCamera(CameraUpdateFactory
        .newLatLng(marker.getPosition()));
  }

  public MarkerOptions getMarker() {
    return marker;
  }

  @Override
  public void onMarkerDragStart(Marker marker) {

  }

  @Override
  public void onMarkerDrag(Marker marker) {

  }

  @Override
  public void onMarkerDragEnd(Marker marker) {
      this.marker.position(marker.getPosition());
  }
}
