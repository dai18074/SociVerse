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
        CheckBox checkBoxFb = view.findViewById(R.id.checkBoxFb);
        CheckBox checkBoxIg = view.findViewById(R.id.checkBoxIg);
        EditText txtPost = view.findViewById(R.id.txtPost);

        buttonPost.setOnClickListener(v -> {
            String postText = txtPost.getText().toString();
            Boolean isItPost = radioPost.isChecked();
            if (checkBoxTwitter.isChecked())
                // Post on twitter
            if (checkBoxFb.isChecked())
                // Post on fb
            if (checkBoxIg.isChecked())
                // Post on ig

            System.out.println("I clicked this button!");
            // depending on type and social media, make a new post/story
        });

    }
}