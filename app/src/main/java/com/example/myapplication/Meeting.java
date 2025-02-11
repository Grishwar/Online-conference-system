package com.example.myapplication;
import java.util.Date;
// Meeting.java
import java.io.Serializable;
import java.util.Date;

public class Meeting implements Serializable {
    private String title;
    private Date dateTime;

    public Meeting(String title, Date dateTime) {
        this.title = title;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public Date getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return title + " - " + dateTime.toString();
    }
}


