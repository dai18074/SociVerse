package com.example.socialmanager.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.socialmanager.R;
import com.example.socialmanager.apiTasks.InstagramPostTask;
import com.example.socialmanager.apiTasks.InstagramStoryTask;
import com.example.socialmanager.apiTasks.TwitterPostTask;
import com.example.socialmanager.utils.SharedViewModel;

import java.io.File;

import twitter4j.StatusUpdate;

import static android.app.Activity.RESULT_OK;

public class CreateFragment extends Fragment {

    private static final int SELECT_PICTURE = 1;
    private SharedViewModel sharedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonPost = view.findViewById(R.id.btnPost);
        RadioGroup typeOfPost = view.findViewById(R.id.groupPostTypes);
        RadioButton radioPost = view.findViewById(R.id.radioPost);
        CheckBox checkBoxTwitter = view.findViewById(R.id.checkBoxTwitter);
        CheckBox checkBoxIg = view.findViewById(R.id.checkBoxIg);
        TextView txtFilePath = view.findViewById(R.id.txtFilePath);
        Button buttonGetPhoto = view.findViewById(R.id.btnGetPhoto);
        EditText txtPost = view.findViewById(R.id.txtPost);

        buttonGetPhoto.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
        });

        buttonPost.setOnClickListener(v -> {
            String postText = txtPost.getText().toString();
            boolean isItPost = radioPost.isChecked();
            String filePath = txtFilePath.getText().toString();

            if (checkBoxTwitter.isChecked() && isItPost){
                StatusUpdate status = new StatusUpdate(postText);

                if (!filePath.isEmpty()){
                    status.setMedia(new File(Uri.parse(filePath).getPath()));
                }
                newPostTwitter(status);
            }
            if (checkBoxIg.isChecked()){
                if (!filePath.isEmpty()){
                    File imageFile = new File(Uri.parse(filePath).getPath());

                    if(isItPost)
                        newPostIg(imageFile, postText);
                    else
                        newStoryIg(imageFile);
                }
            }
        });

        // reload saved state
        txtFilePath.setText(sharedViewModel.getFilePath());
        txtPost.setText(sharedViewModel.getPostText());
        checkBoxTwitter.setChecked(sharedViewModel.getTwitterChecked());
        checkBoxIg.setChecked(sharedViewModel.getIgChecked());
        if (sharedViewModel.getPostChecked())
            typeOfPost.check(R.id.radioPost);
        else
            typeOfPost.check(R.id.radioStory);
    }

    private void newPostTwitter(StatusUpdate status){
        TwitterPostTask postTwitterTask = new TwitterPostTask();
        postTwitterTask.execute(status);
    }

    private void newPostIg(File imageFile, String message){
        InstagramPostTask postIgTask = new InstagramPostTask();
        postIgTask.execute(imageFile, message);
    }

    private void newStoryIg(File imageFile){
        InstagramStoryTask storyIgTask = new InstagramStoryTask();
        storyIgTask.execute(imageFile);
    }

    @Override
    public void onStop() {
        super.onStop();

        sharedViewModel.setPostText(
                ((EditText)getView().findViewById(R.id.txtPost)).getText().toString()
        );
        sharedViewModel.setTwitterChecked(
                ((CheckBox)getView().findViewById(R.id.checkBoxTwitter)).isChecked()
        );
        sharedViewModel.setIgChecked(
                ((CheckBox)getView().findViewById(R.id.checkBoxIg)).isChecked()
        );
        sharedViewModel.setPostChecked(
                ((RadioButton)getView().findViewById(R.id.radioPost)).isChecked()
        );
        sharedViewModel.setFilePath(
                ((TextView)getView().findViewById(R.id.txtFilePath)).getText().toString()
        );
        getView().findViewById(R.id.txtPost);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageURI = data.getData();
                TextView filePath = getView().findViewById(R.id.txtFilePath);

                filePath.setText(getPath(selectedImageURI));
            }
        }
    }

    private String getPath(Uri uri) {
        String path;
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        path = cursor.getString(column_index);
        return path;
    }
}