package com.example.android.newsapp;

public class News {

    private String mSection;
    private String mTitle;
    private String mDate;
    private String mTime;
    private String mUrl;
    private String mAuthor;

    public News(String section, String title, String time, String url, String author){
        mSection = section;
        mTitle = title;
//        mDate = date;
        mTime = time;
        mUrl = url;
        mAuthor = author;
    }

    public String getSection() {
        return mSection;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getTime() {
        return mTime;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
