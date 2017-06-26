package com.gabriellcosta.mymapsapp.data;

import java.util.List;

/**
 * Created by gabrielcosta on 25/06/17.
 */

public interface FavoriteDAO {

  void insert(final FavoritePlaceVO vo);

  void delete(final Integer id);

  List<FavoritePlaceVO> fetch();

  void close();

}
