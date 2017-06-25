package com.gabriellcosta.mymapsapp;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.maps.model.LatLng;

public class PreferenceManager {

  private static final String LAT_KEY = "LAT_KEY";
  private static final String LNG_KEY = "LNG_KEY";
  private static final String PREFERENCE_NAME = "MAPS_PREFERENCE";
  private static final String DEF_VALUE = "0.0";

  private SharedPreferences preferences;

  public PreferenceManager(final Context context) {
    this.preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
  }

  public void saveLocation(final LatLng position) {
    preferences.edit()
        .putString(LAT_KEY, String.valueOf(position.latitude))
        .putString(LNG_KEY, String.valueOf(position.longitude))
        .apply();
  }

  public LatLng getLocation() {
    final String lat = preferences.getString(LAT_KEY, DEF_VALUE);
    final String lng = preferences.getString(LNG_KEY, DEF_VALUE);
    return new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
  }


}
