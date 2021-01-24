package com.example.socialmanager.utils;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private List<String> trends;
    private List<Post> posts;
    private String filePath;
    private String searchHashtag;
    private String postText;
    private Boolean twitterChecked;
    private Boolean igChecked;
    private Boolean postChecked;

    public SharedViewModel() {
        trends = new ArrayList<>();
        posts = new ArrayList<>();
        filePath = "";
        searchHashtag = "";
        postText = "";
        twitterChecked = false;
        igChecked = false;
        postChecked = true;
    }

    public List<String> getTrends() {
        return trends;
    }

    public void setTrends(List<String> trends) {
        this.trends = trends;
    }

    public List<Post> getPosts() { return posts; }

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

    public String getPostText() { return postText; }

    public void setPostText(String postText) { this.postText = postText; }

    public Boolean getTwitterChecked() { return twitterChecked; }

    public void setTwitterChecked(Boolean twitterChecked) { this.twitterChecked = twitterChecked; }

    public Boolean getIgChecked() { return igChecked; }

    public void setIgChecked(Boolean igChecked) { this.igChecked = igChecked; }

    public Boolean getPostChecked() { return postChecked; }

    public void setPostChecked(Boolean postChecked) { this.postChecked = postChecked; }
}
