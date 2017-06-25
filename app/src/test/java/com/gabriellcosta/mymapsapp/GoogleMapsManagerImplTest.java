package com.gabriellcosta.mymapsapp;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest({GoogleMap.class, MarkerOptions.class, CameraUpdateFactory.class, LatLng.class})
public class GoogleMapsManagerImplTest {

  @Rule public PowerMockRule rule = new PowerMockRule();

  private GoogleMapsManager manager;
  private GoogleMap googleMap;
  private MarkerOptions markerOptions;

  @Before
  public void setUp() throws Exception {
    googleMap = mock(GoogleMap.class);
    PowerMockito.mockStatic(CameraUpdateFactory.class);
    markerOptions = new MarkerOptions().position(new LatLng(0,0));
    manager = new GoogleMapsManagerImpl(googleMap, markerOptions);
  }

  @Test
  public void whenUpdated_ShouldClearMarkers() {
    manager.update();
    verify(googleMap, times(1)).clear();
  }

  @Test
  public void whenUpdated_ShouldAddMarker() {
    manager.update();
    verify(googleMap, times(1)).addMarker(markerOptions);
  }

  @Test
  public void whenUpdated_ShouldMoveCameraToMarkerPosition() {
    manager.update();
    verify(googleMap, times(1)).animateCamera(any(CameraUpdate.class));
  }

  @Test
  public void whenUpdateMark_ShouldChangeMarkerPosition() {
    final Place mock = mock(Place.class);
    final LatLng expectedPosition = new LatLng(99, 99);
    when(mock.getLatLng()).thenReturn(expectedPosition);
    manager.update(mock);
    final LatLng actualPosition = manager.getMarker().getPosition();
    assertEquals(expectedPosition, actualPosition);
    verify(googleMap).clear();
    verify(googleMap).addMarker(markerOptions.position(expectedPosition));
    verify(googleMap).animateCamera(any(CameraUpdate.class));
  }

}