package com.gabriellcosta.mymapsapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public final class FavoritePlacesDBHelper extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;
  static final String DATABASE_NAME = "mymap.places.db";

  public FavoritePlacesDBHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(FavoriteEntry.CREATE_TABLE);
    addDefaultValues(db);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(FavoriteEntry.DROP_TABLE);
    onCreate(db);
  }


  private void addDefaultValues(SQLiteDatabase db) {
    ArrayList<FavoritePlaceVO> list = new ArrayList<>();
    list.add(new FavoritePlaceVO(0, "Parque Villa-Lobos", -23.547867, -46.725070));
    list.add(new FavoritePlaceVO(0, "Parque Ibirapuera", -23.588586, -46.658058));
    list.add(new FavoritePlaceVO(0, "Parque do Povo", -23.587888, -46.688686));
    list.add(new FavoritePlaceVO(0, "Parque da Água Branca", -23.530696, -46.669960));

    for (FavoritePlaceVO item : list) {
      insert(db, item);
    }
  }

  private void insert(SQLiteDatabase db, final FavoritePlaceVO favoritePlaceVO) {
    ContentValues values = new ContentValues();
    values.put(FavoriteEntry.NAME, favoritePlaceVO.getName());
    values.put(FavoriteEntry.LATITUDE, favoritePlaceVO.getLatitute());
    values.put(FavoriteEntry.LONGITUDE, favoritePlaceVO.getLongitude());

    db.insert(FavoriteEntry.TABLE_NAME, null, values);
  }

}
