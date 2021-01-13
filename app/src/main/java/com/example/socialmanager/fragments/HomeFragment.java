package com.example.socialmanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.socialmanager.R;
import com.example.socialmanager.adapters.TrendsAdapter;
import com.example.socialmanager.apiTasks.TwitterTrendsTask;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private TrendsAdapter trendsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(R.id.listHome);

        trendsAdapter = new TrendsAdapter(getActivity(), R.layout.trend, new ArrayList<String>());
        listView.setAdapter(trendsAdapter);

        fetchTrends();
    }

    private void fetchTrends(){
        TwitterTrendsTask fetchTrendsTask = new TwitterTrendsTask(trendsAdapter);
        fetchTrendsTask.execute();
    }
}