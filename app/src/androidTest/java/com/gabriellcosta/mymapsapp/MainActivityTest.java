package com.gabriellcosta.mymapsapp;


import static android.support.test.InstrumentationRegistry.getInstrumentation;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import org.junit.Before;
import org.junit.Test;

public class MainActivityTest {

  private UiDevice device;

  @Before
  public void setup() {
    device = UiDevice.getInstance(getInstrumentation());
    AppLauncherUtil.launchApp(device);
  }

  @Test
  public void shouldShowMarkerWhenStarted() throws UiObjectNotFoundException {
    device.findObject(new UiSelector().descriptionContains(MarkerFactory.MARKER_TITLE));
  }

}