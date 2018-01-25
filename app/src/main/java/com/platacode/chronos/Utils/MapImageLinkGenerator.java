package com.platacode.chronos.Utils;

import android.widget.ImageView;

public class MapImageLinkGenerator {

    private double latitude;
    private double longitude;
    private int height;
    private int width;
    private static final String apiKey = "AIzaSyDJLE_imVHScpRH1UgoqRZcQ87eCyTpl04";
    private static final String hostName = "https://maps.googleapis.com/maps/api/staticmap?";
    private int zoomLevel;

    public MapImageLinkGenerator(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

        this.height = 400;
        this.width = 660;
        this.zoomLevel = 17;
    }

    public String generateUrl() {
        return hostName + "center=" + latitude + "," + longitude
                + "&zoom=" + zoomLevel
                + "&size=" + width + "x" + height
                + "&markers=color:red|label:A|" + latitude + "," + longitude
                + "&key=" + apiKey;
    }

    public MapImageLinkGenerator setHeight(int height) {
        this.height = height;

        return this;
    }

    public MapImageLinkGenerator setWidth(int width) {
        this.width = width;

        return this;
    }

    public void setMapImageTo(ImageView imageView) {
        new DownloadImageTask(imageView).execute(generateUrl());
    }
}
