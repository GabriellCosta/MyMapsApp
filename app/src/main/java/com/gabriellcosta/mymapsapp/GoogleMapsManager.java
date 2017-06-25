package com.gabriellcosta.mymapsapp;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by gabrielcosta on 24/06/17.
 */

public interface GoogleMapsManager {

  /**
   * Update google maps position placing a default Marker on it
   */
  void update();

  /**
   * Update google maps positions placing a Marker with a given position
   * @param place
   */
  void update(Place place);

  /**
   * Move camera to Marker
   */
  void moveToMarker();

  /**
   * Get the placed Marker
   * @return Placed Marker
   */
  MarkerOptions getMarker();


}
