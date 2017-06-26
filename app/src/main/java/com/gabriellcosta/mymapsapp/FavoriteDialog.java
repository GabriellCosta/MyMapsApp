package com.gabriellcosta.mymapsapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import com.gabriellcosta.mymapsapp.data.FavoritePlaceVO;
import java.util.List;

public final class FavoriteDialog {

  private AlertDialog showed;

  public void showDialog(final Context context, final List<FavoritePlaceVO> list,
      final DialogItemClick listener) {
    final AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
    builderSingle.setTitle(R.string.main_dialog_title);

    final ArrayAdapter<FavoritePlaceVO> arrayAdapter = new CustomArrayAdapter(context,
        new CustomArrayAdapter.AdapterClickListener() {
          @Override
          public void itemClick(FavoritePlaceVO item) {
            listener.dialogItemChoosed(item);
            showed.dismiss();
          }

          @Override
          public void deleteItemClick(FavoritePlaceVO item) {
            listener.deletedItemChoosed(item);
          }
        });
    arrayAdapter.addAll(list);

    builderSingle.setNegativeButton(R.string.main_dialog_negative, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
      }
    });

    builderSingle.setAdapter(arrayAdapter, new OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
      }
    });
    showed = builderSingle.show();
  }

  interface DialogItemClick {

    void dialogItemChoosed(final FavoritePlaceVO item);

    void deletedItemChoosed(final FavoritePlaceVO item);

  }

}
