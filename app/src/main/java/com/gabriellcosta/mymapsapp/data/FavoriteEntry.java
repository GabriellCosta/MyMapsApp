package com.gabriellcosta.mymapsapp.data;

import android.provider.BaseColumns;

final class FavoriteEntry implements BaseColumns {

  static final String TABLE_NAME = "TABLE_FAVORITE";
  static final String NAME = "NAME";
  static final String LATITUDE = "LATITUDE";
  static final String LONGITUDE = "LONGITUDE";

  static final int ID_INDEX = 0;
  static final int NAME_INDEX = 1;
  static final int LATITUDE_INDEX = 2;
  static final int LONGITUDE_INDEX = 3;

  static final String CREATE_TABLE = String.format(
      "CREATE TABLE %s (%s INTEGER PRIMARY KEY,%s TEXT NOT NULL,%s REAL unique NOT NULL,%s REAL unique NOT NULL);",
      TABLE_NAME, _ID, NAME, LATITUDE, LONGITUDE);

  static final String DROP_TABLE = "DROP TABLE IF EXISTS" + TABLE_NAME;

}
