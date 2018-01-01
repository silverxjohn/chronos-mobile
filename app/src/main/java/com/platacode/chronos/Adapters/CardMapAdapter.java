package com.platacode.chronos.Adapters;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.platacode.chronos.DataContext;
import com.platacode.chronos.Models.Coordinate;
import com.platacode.chronos.Models.History;
import com.platacode.chronos.R;

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

//        MapView mapView = (MapView) cardView.findViewById(R.id.map_view);

        TextView titleView = (TextView) cardView.findViewById(R.id.title);
        titleView.setText(history.getName());

        TextView descriptionView = (TextView) cardView.findViewById(R.id.description);
        descriptionView.setText(history.getCreatedAt());
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
