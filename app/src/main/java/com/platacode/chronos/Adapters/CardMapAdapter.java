package com.platacode.chronos.Adapters;


import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.platacode.chronos.DataContext;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Models.Coordinate;
import com.platacode.chronos.Models.History;
import com.platacode.chronos.Models.TimeLog;
import com.platacode.chronos.R;
import com.platacode.chronos.Utils.DownloadImageTask;
import com.platacode.chronos.Utils.MapImageLinkGenerator;

import java.util.ArrayList;
import java.util.List;

public class CardMapAdapter extends RecyclerView.Adapter<CardMapAdapter.ViewHolder> {

    private List<TimeLog> timelogs;

    public CardMapAdapter(List<TimeLog> timelogs) {
        this.timelogs = timelogs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_map, parent, false);
        Button button = (Button) cardView.findViewById(R.id.button2);

        return new ViewHolder(cardView, button);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CardView cardView = holder.cardView;
        final  Button button = holder.button;
        final TimeLog timelog = timelogs.get(position);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=" + timelog.getLatitude() + "," + timelog.getLongitude());

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                cardView.getContext().startActivity(mapIntent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=" + timelog.getLatitude() + "," + timelog.getLongitude());

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                cardView.getContext().startActivity(mapIntent);
            }
        });

        TimeLog timeLog = timelogs.get(position);

        ImageView imageView = (ImageView) cardView.findViewById(R.id.mapImageView);

        new MapImageLinkGenerator(Double.valueOf(timeLog.getLatitude()), Double.valueOf(timeLog.getLongitude()))
                .setHeight(253)
                .setWidth(459)
                .setMapImageTo(imageView);

        TextView titleView = (TextView) cardView.findViewById(R.id.title);
        titleView.setText(timeLog.getName());

        TextView descriptionView = (TextView) cardView.findViewById(R.id.description);
        descriptionView.setText(timeLog.getCreated_at());
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
        return timelogs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private Button button;

        public ViewHolder(CardView cardView, Button button) {
            super(cardView);
            this.cardView = cardView;
            this.button = button;
        }
    }
}



