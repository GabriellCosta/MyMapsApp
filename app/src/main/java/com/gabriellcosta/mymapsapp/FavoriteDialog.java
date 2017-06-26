package com.gabriellcosta.mymapsapp;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import com.gabriellcosta.mymapsapp.data.FavoritePlaceVO;
import java.util.List;

/**
 * Created by gabrielcosta on 26/06/17.
 */

public final class FavoriteDialog {

  public static void showDialog(final Context context, final List<FavoritePlaceVO> list,
      final DialogItemClick listener) {
    AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
    builderSingle.setTitle(R.string.main_dialog_title);

    final ArrayAdapter<FavoritePlaceVO> arrayAdapter = new ArrayAdapter<>(context,
        android.R.layout.select_dialog_singlechoice);
    arrayAdapter.addAll(list);

    builderSingle.setNegativeButton(R.string.main_dialog_negative, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
      }
    });

    builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        FavoritePlaceVO item = list.get(which);
        listener.dialogItemChoosed(item);
      }
    });
    builderSingle.show();
  }

  interface DialogItemClick {

    void dialogItemChoosed(final FavoritePlaceVO item);

    void deletedItemChoosed(final FavoritePlaceVO item);

  }

}
