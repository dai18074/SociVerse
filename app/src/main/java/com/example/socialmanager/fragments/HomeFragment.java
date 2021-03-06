package com.example.socialmanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.socialmanager.R;
import com.example.socialmanager.adapters.TrendsAdapter;
import com.example.socialmanager.apiTasks.TwitterTrendsTask;
import com.example.socialmanager.utils.SharedViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private TrendsAdapter trendsAdapter;
    private SharedViewModel sharedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trendsAdapter = new TrendsAdapter(getActivity(), R.layout.trend, new ArrayList<>());
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        ListView listView = view.findViewById(R.id.listHome);
        listView.setAdapter(trendsAdapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            String trend = trendsAdapter.getTrend(position);

            sharedViewModel.setPosts(new ArrayList<>());
            sharedViewModel.setSearchHashtag(trend.replace("#", ""));
            navView.setSelectedItemId(R.id.navigation_search);
        });

        // check if trends are already saved in sharedViewModel
        List<String> trends = sharedViewModel.getTrends();
        if (trends.isEmpty()){
            fetchTrends();
        } else {
            trendsAdapter.setTrends(trends);
        }
    }

    private void fetchTrends(){
        TwitterTrendsTask fetchTrendsTask = new TwitterTrendsTask(trendsAdapter);
        fetchTrendsTask.execute();
    }

    @Override
    public void onStop() {
        super.onStop();
        sharedViewModel.setTrends(trendsAdapter.getTrends());
    }
}