package com.gabriellcosta.mymapsapp;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;

public final class AppLauncherUtil {

  private static final int LAUNCH_TIMEOUT = 5000;

  private AppLauncherUtil() {
  }

  public static void launchApp(final UiDevice device) {
    // Start from the home screen
    device.pressHome();

    // Wait for launcher
    final String launcherPackage = device.getLauncherPackageName();
    assertThat(launcherPackage, notNullValue());
    device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
        LAUNCH_TIMEOUT);

    // Launch the app
    Context context = InstrumentationRegistry.getContext();
    final Intent intent = context.getPackageManager()
        .getLaunchIntentForPackage(BuildConfig.APPLICATION_ID);
    // Clear out any previous instances
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
    context.startActivity(intent);

    // Wait for the app to appear
    device.wait(Until.hasObject(By.pkg(BuildConfig.APPLICATION_ID).depth(0)),
        LAUNCH_TIMEOUT);
  }

}
