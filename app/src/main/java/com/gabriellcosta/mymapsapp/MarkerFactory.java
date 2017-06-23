package com.gabriellcosta.mymapsapp;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public final class MarkerFactory {

  public static final String MARKER_TITLE = "SimpleMarker";

  private MarkerFactory() {
    throw new RuntimeException();
  }

  /**
   * Create a simple {@link MarkerOptions} with a position and Draggable
   * @param lat latitude
   * @param lng longitude
   * @return {@link MarkerOptions}
   */
  public static MarkerOptions createSimpleMarker(final double lat, final double lng) {
    return new MarkerOptions()
        .draggable(true)
        .position(new LatLng(lat, lng))
        .title(MARKER_TITLE);
  }
}
