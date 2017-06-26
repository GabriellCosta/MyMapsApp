package com.gabriellcosta.mymapsapp.data;

import static junit.framework.Assert.assertEquals;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class FavoriteDAOImplTest {

  private FavoriteDAO dao;
  private Context context;
  private FavoritePlacesDBHelper database;

  @Before
  public void setup() {
    context = InstrumentationRegistry.getTargetContext();
    context.deleteDatabase(FavoritePlacesDBHelper.DATABASE_NAME);
    database = new FavoritePlacesDBHelper(context);
    dao = new FavoriteDAOImpl(database);
  }

  @Test
  public void shouldSaveInsertedValuesOnDatabase() {
    FavoritePlaceVO mock = new FavoritePlaceVO(5, "MOCK", 44.968046,   -94.420307);
    FavoritePlaceVO mock2 = new FavoritePlaceVO(6, "MOCK2", 14.0, 11.0);
    dao.insert(mock);
    dao.insert(mock);
    dao.insert(mock2);

    final List<FavoritePlaceVO> fetch = dao.fetch();
    assertEquals(6, fetch.size());

    assertEquals(mock, fetch.get(4));
    assertEquals(mock2, fetch.get(5));

    dao.delete(1);

    List<FavoritePlaceVO> fetchAfterDelete = dao.fetch();

    assertEquals(5, fetchAfterDelete.size());
    assertEquals(mock, fetch.get(4));

    dao.delete(1);
  }

}