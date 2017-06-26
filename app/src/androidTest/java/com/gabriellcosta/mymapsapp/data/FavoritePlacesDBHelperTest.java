package com.gabriellcosta.mymapsapp.data;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public final class FavoritePlacesDBHelperTest {

  private Context context;
  private static final int COLUMN_NOT_EXISTS = -1;

  @Before
  public void setup() {
    context = InstrumentationRegistry.getTargetContext();
    context.deleteDatabase(FavoritePlacesDBHelper.DATABASE_NAME);
  }

  @Test
  public void shouldCreateDatabase() {
    final SQLiteDatabase db = new FavoritePlacesDBHelper(context).getWritableDatabase();
    assertEquals(true, db.isOpen());
  }

  @Test
  public void shouldHaveFavoriteTable() {
    final SQLiteDatabase readableDatabase = new FavoritePlacesDBHelper(context)
        .getReadableDatabase();
    Cursor query = readableDatabase
        .rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

    assertTrue("Database was not created correctly", query.moveToFirst());

    Set<String> favoriteSet = new HashSet<>();
    favoriteSet.add(FavoriteEntry.TABLE_NAME);

    do {
      favoriteSet.remove(query.getString(0));
    } while (query.moveToNext());

    assertTrue("Favorite Table was not created", favoriteSet.isEmpty());

  }

  @Test
  public void shouldHaveIdColumn() {
    validateColumn(FavoriteEntry._ID);
  }

  @Test
  public void shouldHaveNameColumn() {
    validateColumn(FavoriteEntry.NAME);
  }

  @Test
  public void shouldHaveLongitudeColumn() {
    validateColumn(FavoriteEntry.LONGITUDE);
  }

  @Test
  public void shouldHaveLatitudeColumn() {
    validateColumn(FavoriteEntry.LATITUDE);
  }

  private void validateColumn(final String column) {
    final SQLiteDatabase readableDatabase = new FavoritePlacesDBHelper(context).getReadableDatabase();
    Cursor query = readableDatabase
        .query(FavoriteEntry.TABLE_NAME, null, null, null, null, null, null, null);

    assertTrue("Column does not exist",
        COLUMN_NOT_EXISTS != query.getColumnIndex(column));
    query.close();
  }

}