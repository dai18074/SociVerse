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
import com.example.socialmanager.adapters.SearchAdapter;
import com.example.socialmanager.apiTasks.TwitterSearchTask;
import com.example.socialmanager.apiTasks.TwitterTrendsTask;
import com.example.socialmanager.utils.Post;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private SearchAdapter searchAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(R.id.listSearch);

        searchAdapter = new SearchAdapter(getActivity(), R.layout.post, new ArrayList<Post>());
        listView.setAdapter(searchAdapter);

        ImageButton buttonSearch = view.findViewById(R.id.btnSearch);
        EditText txtSearch = view.findViewById(R.id.txtSearch);

        buttonSearch.setOnClickListener(v -> {
            String queryString = txtSearch.getText().toString();
            searchPosts(queryString);
        });
    }

    private void searchPosts(String queryString){
        TwitterSearchTask fetchPostsTask = new TwitterSearchTask(searchAdapter);
        fetchPostsTask.execute(queryString);
    }

}