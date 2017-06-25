package com.hackathon.kosicko.handlers;

/**
 * Created by Martin on 11/27/2016.
 */

public class Events {

    private int id;
    private String name;
    private String url;
    private String date;
    private String time;
    private String place;
    private String category;

    public Events(){

    }

    public Events(String category, String name, String url, String date, String time, String place) {
        this.category = category;
        this.name = name;
        this.url = url;
        this.date = date;
        this.time = time;
        this.place = place;
    }

    public Events(int id, String category, String name, String url, String date, String time, String place) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.url = url;
        this.date = date;
        this.time = time;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
