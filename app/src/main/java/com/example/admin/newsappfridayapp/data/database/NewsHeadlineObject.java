package com.example.admin.newsappfridayapp.data.database;

/**
 * Created by  Admin on 12/9/2017.
 */

public class NewsHeadlineObject {
    String title;
    String DatePublish;

    public NewsHeadlineObject(String string) {
        this.title= string;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatePublish() {
        return DatePublish;
    }

    public void setDatePublish(String datePublish) {
        DatePublish = datePublish;
    }

    public NewsHeadlineObject(String title, String datePublish) {
        this.title = title;
        DatePublish = datePublish;
    }
}
