package com.example.socialmanager.utils;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private List<String> trends;
    private List<Post> posts;
    private String filePath;
    private String searchHashtag;

    public SharedViewModel() {
        trends = new ArrayList<>();
        posts = new ArrayList<>();
        filePath = "";
        searchHashtag = "";
    }

    public List<String> getTrends() {
        return trends;
    }

    public void setTrends(List<String> trends) {
        this.trends = trends;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSearchHashtag() {
        return searchHashtag;
    }

    public void setSearchHashtag(String searchHashtag) {
        this.searchHashtag = searchHashtag;
    }
}
