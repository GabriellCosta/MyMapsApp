package com.gabriellcosta.mymapsapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

final class FavoritePlacesDBHelper extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;
  static final String DATABASE_NAME = "mymap.places.db";

  public FavoritePlacesDBHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(FavoriteEntry.CREATE_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(FavoriteEntry.DROP_TABLE);
    onCreate(db);
  }


}
