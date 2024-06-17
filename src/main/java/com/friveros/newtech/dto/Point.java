package com.friveros.newtech.dto;

import java.util.List;

public class Point {
    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Double> coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String type;

    public Point(String type, List<Double> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Point{" +
                "coordinates=" + coordinates.toString() +
                ", type='" + type + '\'' +
                '}';
    }
}
