package com.example.socialmanager.apiTasks;

import android.os.AsyncTask;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterPostTask extends AsyncTask<Object, Void, Void> {

    protected Void doInBackground(Object... params) {
        StatusUpdate status = (twitter4j.StatusUpdate) params[0];
        Twitter twitter = new TwitterFactory().getInstance();

        try {
            twitter.updateStatus(status);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to post tweet: " + te.getMessage());
        }
        return null;
    }
}
