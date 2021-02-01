package com.example.socialmanager.fragments;

import android.content.Intent;
import android.net.Uri;
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
import androidx.lifecycle.ViewModelProvider;

import com.example.socialmanager.R;
import com.example.socialmanager.adapters.SearchAdapter;
import com.example.socialmanager.apiTasks.TwitterSearchTask;
import com.example.socialmanager.utils.Post;
import com.example.socialmanager.utils.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private SearchAdapter searchAdapter;
    private SharedViewModel sharedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchAdapter = new SearchAdapter(getActivity(), R.layout.post, new ArrayList<>());
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton buttonSearch = view.findViewById(R.id.btnSearch);
        EditText txtSearch = view.findViewById(R.id.txtSearch);
        ListView listView = view.findViewById(R.id.listSearch);
        listView.setAdapter(searchAdapter);

        buttonSearch.setOnClickListener(v -> {
            String queryString = txtSearch.getText().toString();

            sharedViewModel.setSearchHashtag(queryString);
            searchPosts(queryString);
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Post newsEntry = searchAdapter.getPost(position);
            String url = newsEntry.getUrl();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        });

        // check if posts are already saved in sharedViewModel
        List<Post> posts = sharedViewModel.getPosts();
        String searchHashtag = sharedViewModel.getSearchHashtag();
        if (posts.isEmpty() && !searchHashtag.isEmpty()){
            txtSearch.setText(searchHashtag);
            searchPosts(searchHashtag);
        } else {
            txtSearch.setText(searchHashtag);
            searchAdapter.setPosts(posts);
        }
    }

    private void searchPosts(String queryString){
        TwitterSearchTask fetchPostsTask = new TwitterSearchTask(searchAdapter);
        fetchPostsTask.execute(queryString);
    }

    @Override
    public void onStop() {
        super.onStop();
        sharedViewModel.setPosts(searchAdapter.getPosts());
    }
}