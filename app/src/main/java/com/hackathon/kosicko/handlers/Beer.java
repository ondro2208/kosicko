package com.hackathon.kosicko.handlers;

/**
 * Created by Peťo on 27.11.2016.
 */

public class Beer {

    //Private variables
    private String lat;
    private String lng;
    private int people;

    //empty constructor
    public Beer(){

    }

    public Beer(String lat, String lng, int people) {
        this.lat = lat;
        this.lng = lng;
        this.people = people;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }
}
