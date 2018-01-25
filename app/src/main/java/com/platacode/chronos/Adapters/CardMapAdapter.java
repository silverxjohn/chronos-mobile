package com.platacode.chronos.Adapters;


import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.platacode.chronos.DataContext;
import com.platacode.chronos.Models.Coordinate;
import com.platacode.chronos.Models.History;
import com.platacode.chronos.R;
import com.platacode.chronos.Utils.DownloadImageTask;
import com.platacode.chronos.Utils.MapImageLinkGenerator;

import java.util.ArrayList;

public class CardMapAdapter extends RecyclerView.Adapter<CardMapAdapter.ViewHolder> {

    private ArrayList<Coordinate> coordinates;

    private ArrayList<History> histories;

    public CardMapAdapter(ArrayList<Coordinate> coordinates, ArrayList<History> histories) {
        this.coordinates = coordinates;
        this.histories = histories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_map, parent, false);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CardView cardView = holder.cardView;

        History history = histories.get(position);
        Coordinate coordinate = coordinates.get(position);

        ImageView imageView = (ImageView) cardView.findViewById(R.id.mapImageView);

        new MapImageLinkGenerator(14.640173, 120.972679)
                .setHeight(253)
                .setWidth(458)
                .setMapImageTo(imageView);

        TextView titleView = (TextView) cardView.findViewById(R.id.title);
        titleView.setText(history.getName());

        TextView descriptionView = (TextView) cardView.findViewById(R.id.description);
        descriptionView.setText(history.getCreatedAt());
    }

    public static int convertDpToPixel(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    public static int getScreenWidth() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();

        return metrics.widthPixels;
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}
