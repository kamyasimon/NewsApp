package com.hipipo.newsapp;


import android.os.AsyncTask;

import java.io.IOException;

public class News {
    private String newsTopic;
    private String newsPublicationDate;
    private String newsSection;

    public News(String newsTopic, String newsPublicationDate, String newsSection) {
        this.newsTopic = newsTopic;
        this.newsPublicationDate = newsPublicationDate;
        this.newsSection = newsSection;
    }

    public String getnewsTopic() {

        return newsTopic;
    }

    public String getnewsPublicationDate() {
        return newsPublicationDate;
    }
    public String getNewsSection() {
        return newsSection;
    }

}
