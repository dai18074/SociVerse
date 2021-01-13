package com.example.socialmanager.utils;

public class Post {
    // twitter/instagram/facebook
    private String date;
    private String type;
    private String user;
    private String text;
    private String url;
    private String urlToImage;

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    @Override
    public String toString() {
        return "NewsEntry{" +
                "user='" + user + '\'' +
                ", description='" + ( text.length() > 20 ? text.substring(0,20) : text) + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
