package com.example.socialmanager.apiTasks;

import android.os.AsyncTask;

import java.io.File;

import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterPostTask extends AsyncTask<Object, Void, Void> {

    protected Void doInBackground(Object... params) {
        String message = (String) params[0];
        Boolean hasImage = (Boolean) params[1];
        Twitter twitter = new TwitterFactory().getInstance();

        try {
            StatusUpdate status = new StatusUpdate(message);
            if (hasImage)
                status.setMedia((File) params[2]);
            twitter.updateStatus(status);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to post tweet: " + te.getMessage());
        }
        return null;
    }
}
