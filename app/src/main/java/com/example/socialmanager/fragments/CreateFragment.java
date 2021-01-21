package com.example.socialmanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.socialmanager.R;
import com.example.socialmanager.apiTasks.TwitterPostTask;

public class CreateFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonPost = view.findViewById(R.id.btnPost);
        RadioButton radioPost = view.findViewById(R.id.radioPost);
        RadioButton radioStory = view.findViewById(R.id.radioStory);
        CheckBox checkBoxTwitter = view.findViewById(R.id.checkBoxTwitter);
        CheckBox checkBoxIg = view.findViewById(R.id.checkBoxIg);
        EditText txtPost = view.findViewById(R.id.txtPost);

        buttonPost.setOnClickListener(v -> {
            String postText = txtPost.getText().toString();
            Boolean isItPost = radioPost.isChecked();
            if (checkBoxTwitter.isChecked()){
                newPostTwitter(postText);
            }
            if (checkBoxIg.isChecked()){}

        });

    }

    private void newPostTwitter(String queryString){
        TwitterPostTask postTwitterTask = new TwitterPostTask();
        postTwitterTask.execute(queryString);
    }
}