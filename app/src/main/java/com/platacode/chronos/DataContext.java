package com.platacode.chronos;


import com.platacode.chronos.Models.Coordinate;
import com.platacode.chronos.Models.History;

import java.util.ArrayList;

public class DataContext {

    public static ArrayList<History> getHistories() {

        ArrayList<History> histories = new ArrayList<>();
        histories.add(new History("Manila", "Yesterday"));
        histories.add(new History("Quezon City", "Now"));

        return histories;
    }

    public static ArrayList<Coordinate> getCoordinates() {
        Coordinate c = new Coordinate(0, 0);

        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(c);
        coordinates.add(c);

        return coordinates;
    }
}
