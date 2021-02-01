package com.example.socialmanager.apiTasks;

import android.os.AsyncTask;

import com.example.socialmanager.adapters.TrendsAdapter;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterTrendsTask extends AsyncTask<String, Void, List<String>> {

    private final TrendsAdapter trendsAdapter;

    public TwitterTrendsTask(TrendsAdapter trendsAdapter) {
        this.trendsAdapter = trendsAdapter;
    }

    @Override
    protected List<String> doInBackground(String... strings) {
        List<String> trendsList = new ArrayList<>();
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            Trends trends = twitter.getPlaceTrends(23424833);
            for (Trend trend : trends.getTrends()) {
                trendsList.add(trend.getName());
            }

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get trends: " + te.getMessage());
        }
        return trendsList;
    }

    @Override
    protected void onPostExecute(List<String> trends) {
        trendsAdapter.setTrends(trends);
    }
}
