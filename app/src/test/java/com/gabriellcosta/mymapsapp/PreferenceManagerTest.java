package com.gabriellcosta.mymapsapp;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.maps.model.LatLng;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class PreferenceManagerTest {

  private static final Double LAT = 13.0;
  private static final Double LNG = 12.0;
  private PreferenceManager preference;
  private SharedPreferences sharedPreferences;

  @Before public void setup() {
    preference = new PreferenceManager(RuntimeEnvironment.application);
    sharedPreferences = getPreference();
  }

  @After public void after() {
    sharedPreferences.edit().clear().apply();
  }

  @Test
  public void whenSavePreference_ShouldSave() {
    final LatLng latLng = new LatLng(LAT, LNG);
    preference.saveLocation(latLng);

    final String latKey = sharedPreferences.getString("LAT_KEY", "TEST FAIL");
    assertEquals(LAT.toString(), latKey);
    final String lngKey = sharedPreferences.getString("LNG_KEY", "TEST_FAIL");
    assertEquals(LNG.toString(), lngKey);

  }

  @Test
  public void whenRetrievePreference_ShouldBeSavedValues() {
    final LatLng latLng = new LatLng(LAT, LNG);
    preference.saveLocation(latLng);

    final LatLng savedLocation = preference.getLocation();
    assertEquals(latLng, savedLocation);
  }

  @Test
  public void whenRetrievePreference_ShouldNotBeNull() {
    assertNotNull(preference.getLocation());
  }

  @Test public void whenRetrieveDefaultPreference_LatLngShouldBe0() {
    final LatLng actual = preference.getLocation();
    assertEquals(new LatLng(0,0), actual);
  }

  private SharedPreferences getPreference() {
    return RuntimeEnvironment.application
        .getSharedPreferences("MAPS_PREFERENCE", Context.MODE_PRIVATE);
  }

}