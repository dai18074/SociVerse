package com.example.socialmanager;

import org.jetbrains.annotations.NotNull;

import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Trends;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterListener;
import twitter4j.TwitterMethod;

public class TwitterUtils {
    private final TwitterListener listener = new TwitterAdapter() {
        @Override
        public void gotPlaceTrends(Trends trends) {
            for (int i = 0; i < trends.getTrends().length; i++) {
                System.out.println(trends.getTrends()[i].getName());
            }
        }

        @Override
        public void updatedStatus(Status status) {
            System.out.println("Successfully updated the status to: " + status.getText());
        }

        @Override
        public void searched(QueryResult queryResult) {
            for (Status status : queryResult.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            }
        }

        @Override
        public void onException(TwitterException te, TwitterMethod method) {
            super.onException(te, method);
                throw new AssertionError("Should not happen");
        }
    };
    // The factory instance is re-usable and thread safe.
    AsyncTwitterFactory factory = new AsyncTwitterFactory();


    public void postOnTwiter(@NotNull StringBuffer tweet) {
        AsyncTwitter asyncTwitter = factory.getInstance();
        asyncTwitter.addListener(listener);

        asyncTwitter.updateStatus(tweet.toString());
    }

    public void getTrends() {
        AsyncTwitter asyncTwitter = factory.getInstance();
        asyncTwitter.addListener(listener);

        asyncTwitter.getPlaceTrends(23424833);
    }

    public void searchForTweets(@NotNull StringBuffer hashtag) {
        AsyncTwitter asyncTwitter = factory.getInstance();
        asyncTwitter.addListener(listener);

        Query query = new Query(hashtag.toString());
        query.count(25); //100 is the max allowed
        asyncTwitter.search(query);
    }

}
