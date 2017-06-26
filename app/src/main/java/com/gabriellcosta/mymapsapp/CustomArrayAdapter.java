package com.gabriellcosta.mymapsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.gabriellcosta.mymapsapp.data.FavoritePlaceVO;

public class CustomArrayAdapter extends ArrayAdapter<FavoritePlaceVO> {

  private final AdapterClickListener listener;

  public CustomArrayAdapter(@NonNull Context context, AdapterClickListener listener) {
    super(context, 0);
    this.listener = listener;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    final FavoritePlaceVO favoritePlaceVO = getItem(position);

    if (convertView == null) {
      convertView = LayoutInflater.from(getContext())
          .inflate(R.layout.dialog_favorite_list, parent, false);
    }

    TextView textView = (TextView) convertView.findViewById(R.id.text_dialog_name);
    View viewById = convertView.findViewById(R.id.button_dialog_delete);

    viewById.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        listener.deleteItemClick(favoritePlaceVO);
        remove(favoritePlaceVO);
      }
    });

    convertView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        listener.itemClick(favoritePlaceVO);

      }
    });

    textView.setText(favoritePlaceVO.toString());

    return convertView;
  }

  private static class FavoriteViewHolder {
    private final TextView name;
    private final Button deleteButton;

    public FavoriteViewHolder(final View view) {
      name = (TextView) view.findViewById(R.id.text_dialog_name);
      deleteButton = (Button) view.findViewById(R.id.button_dialog_delete);
    }
  }

  public interface AdapterClickListener {
    void itemClick(FavoritePlaceVO item);
    void deleteItemClick(FavoritePlaceVO item);
  }
}
