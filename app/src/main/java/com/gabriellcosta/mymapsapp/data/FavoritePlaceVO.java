package com.gabriellcosta.mymapsapp.data;

public final class FavoritePlaceVO {

  private final int id;
  private final String name;
  private final double latitute;
  private final double longitude;

  public FavoritePlaceVO(int id, String name, double latitute, double longitude) {
    this.id = id;
    this.name = name;
    this.latitute = latitute;
    this.longitude = longitude;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getLatitute() {
    return latitute;
  }

  public double getLongitude() {
    return longitude;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FavoritePlaceVO that = (FavoritePlaceVO) o;

    if (id != that.id) {
      return false;
    }
    if (Double.compare(that.latitute, latitute) != 0) {
      return false;
    }
    if (Double.compare(that.longitude, longitude) != 0) {
      return false;
    }
    return name.equals(that.name);

  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = id;
    result = 31 * result + name.hashCode();
    temp = Double.doubleToLongBits(latitute);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(longitude);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return name;
  }
}
