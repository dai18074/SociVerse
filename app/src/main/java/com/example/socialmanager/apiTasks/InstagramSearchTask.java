package com.example.socialmanager.apiTasks;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.socialmanager.BuildConfig;
import com.example.socialmanager.adapters.SearchAdapter;
import com.example.socialmanager.utils.Post;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.models.media.timeline.TimelineImageMedia;
import com.github.instagram4j.instagram4j.models.media.timeline.TimelineMedia;
import com.github.instagram4j.instagram4j.models.user.User;
import com.github.instagram4j.instagram4j.requests.feed.FeedTagRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedTagResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class InstagramSearchTask extends AsyncTask<String, Void, List<Post>> {

    private SearchAdapter searchAdapter;

    public InstagramSearchTask(SearchAdapter searchAdapter) { this.searchAdapter = searchAdapter; }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SimpleDateFormat")
    private List<Post> getPostsFromResponse(List<TimelineMedia> items) {
        List<Post> posts = new ArrayList<>();
        String type = "instagram";
        int i=0;
        for (TimelineMedia item : items) {
            if(i==20) break;
            if (item instanceof TimelineImageMedia) {
                TimelineImageMedia status = (TimelineImageMedia) item;
                String url = "https://www.instagram.com/p/" + status.getCode();

                Post post = new Post();
                post.setText("");
                post.setUser(status.getUser().getFull_name());
                post.setType(type);
                post.setUrl(url);
                post.setDate(new SimpleDateFormat("MM/dd/yyyy").format(new Date(status.getTaken_at() * 1000)));
                post.setUrlToImage(status.getImage_versions2().getCandidates().get(0).getUrl());

                posts.add(post);
                i++;
            }
        }

        return posts;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected List<Post> doInBackground(String... queryStrings) {
        List<Post> list = new ArrayList<>();
        String queryString = queryStrings[0];

        try {
            IGClient client = IGClient.builder().username(BuildConfig.IG_USERNAME).password(BuildConfig.IG_PASSWORD).login();
            FeedTagResponse response = client.sendRequest(new FeedTagRequest(queryString)).join();
            return getPostsFromResponse(response.getItems());
        } catch (IGLoginException e) {
            System.out.println("Failed to get posts from instagram");
            e.printStackTrace();
            return list;
        }
    }

    @Override
    protected void onPostExecute(List<Post> posts) {
        searchAdapter.addPosts(posts);
    }

}
