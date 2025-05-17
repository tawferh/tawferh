package com.example.views.map.placemark;

public class PlacemarkData {
    private final String name;
    private final PlacemarkType type;

    public PlacemarkData(String name, PlacemarkType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public PlacemarkType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "PlacemarkUserData{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
