package com.example.socialmanager.apiTasks;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.socialmanager.adapters.SearchAdapter;
import com.example.socialmanager.utils.Post;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterSearchTask extends AsyncTask<String, Void, List<Post>> {

    private SearchAdapter searchAdapter;
    private String query;

    public TwitterSearchTask(SearchAdapter searchAdapter) { this.searchAdapter = searchAdapter; }


    @SuppressLint("SimpleDateFormat")
    private List<Post> getPostsFromResponse(QueryResult queryResult) {
        List<Post> posts = new ArrayList<>();
        String type = "twitter";

        for (twitter4j.Status status : queryResult.getTweets()) {
            String url= "https://twitter.com/" + status.getUser().getScreenName()
                    + "/status/" + status.getId();

            Post post = new Post();
            post.setText(status.getText());
            post.setUser(status.getUser().getScreenName());
            post.setType(type);
            post.setUrl(url);
            post.setDate(new SimpleDateFormat("MM-dd-yyyy").format(status.getCreatedAt()));

            // check if tweet has any images
            if (status.getMediaEntities().length != 0) {
                // only get the first image of the tweet
                post.setUrlToImage(status.getMediaEntities()[0].getMediaURL());
            } else {
                post.setUrlToImage("");
            }

            posts.add(post);
        }

        return posts;
    }

    @Override
    protected List<Post> doInBackground(String... queryStrings) {
        String queryString = queryStrings[0];
        Twitter twitter = new TwitterFactory().getInstance();

        try {
            Query query = new Query("#" + queryString);
            query.count(20); //100 is the max allowed
            QueryResult queryResult = twitter.search(query);
            this.query = queryString;

            return getPostsFromResponse(queryResult);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get tweets: " + te.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    protected void onPostExecute(List<Post> posts) {
        searchAdapter.setPosts(posts);
        InstagramSearchTask fetchPostsTask = new InstagramSearchTask(searchAdapter);
        fetchPostsTask.execute(this.query);
    }

}
