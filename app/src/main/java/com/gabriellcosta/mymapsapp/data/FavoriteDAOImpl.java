package com.gabriellcosta.mymapsapp.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FavoriteDAOImpl implements FavoriteDAO {

  private final SQLiteOpenHelper database;

  public FavoriteDAOImpl(SQLiteOpenHelper database) {
    this.database = database;
  }

  @Override public void insert(final FavoritePlaceVO favoritePlaceVO) {
    SQLiteDatabase writableDatabase = database.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(FavoriteEntry.NAME, favoritePlaceVO.getName());
    values.put(FavoriteEntry.LATITUDE, favoritePlaceVO.getLatitute());
    values.put(FavoriteEntry.LONGITUDE, favoritePlaceVO.getLongitude());

    writableDatabase.insert(FavoriteEntry.TABLE_NAME, null, values);
  }

  @Override public void delete(final Integer id) {
    SQLiteDatabase writableDatabase = database.getWritableDatabase();
    writableDatabase.delete(FavoriteEntry.TABLE_NAME, FavoriteEntry._ID + " = ?",
        new String[]{id.toString()});
  }

  @Override public List<FavoritePlaceVO> fetch() {
    SQLiteDatabase readableDatabase = database.getReadableDatabase();
    Cursor query = readableDatabase
        .query(FavoriteEntry.TABLE_NAME, null, null, null, null, null, null);

    return query.getCount() > 0 ? parser(query) : Collections.<FavoritePlaceVO>emptyList();
  }

  @Override
  public void close() {
    database.close();
  }

  private List<FavoritePlaceVO> parser(final Cursor cursor) {
    final List<FavoritePlaceVO> list = new ArrayList<>();
    cursor.moveToNext();
    do {
      final int id = cursor.getInt(FavoriteEntry.ID_INDEX);
      final String name = cursor.getString(FavoriteEntry.NAME_INDEX);
      final double latitude = cursor.getDouble(FavoriteEntry.LATITUDE_INDEX);
      final double longitude = cursor.getDouble(FavoriteEntry.LONGITUDE_INDEX);
      final FavoritePlaceVO favoritePlaceVO = new FavoritePlaceVO(id, name, latitude, longitude);
      list.add(favoritePlaceVO);
    } while (cursor.moveToNext());

    return Collections.unmodifiableList(list);
  }

}
