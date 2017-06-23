package com.gabriellcosta.mymapsapp;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import com.google.android.gms.maps.model.MarkerOptions;
import org.junit.Test;

public class MarkerFactoryTest {

  private final MarkerOptions markerOptions = MarkerFactory.createSimpleMarker(0, 0);

  @Test public void whenCreateMarker_ShouldBeDraggable() {
    assertTrue(markerOptions.isDraggable());
  }

  @Test public void whenCreateMarker_ShouldHaveTitleSimpleMarker() {
    assertEquals("SimpleMarker", markerOptions.getTitle());
  }

}