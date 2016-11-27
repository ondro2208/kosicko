package com.hackathon.kosicko.handlers;

/**
 * Created by Peťo on 26.11.2016.
 */

public class News {

    //Private variables
    private String title;
    private String image;
    private String url;
    private String date;

    //empty constructor
    public News(){

    }

    public News(String source, String title, String image, String url, String date) {
        this.title = title;
        this.image = image;
        this.url = url;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
