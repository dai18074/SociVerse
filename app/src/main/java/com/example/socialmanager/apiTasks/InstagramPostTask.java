package com.example.socialmanager.apiTasks;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.socialmanager.BuildConfig;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;

import java.io.File;

public class InstagramPostTask extends AsyncTask<Object, Void, Void> {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected Void doInBackground(Object... params) {
        File imageFile = (File) params[0];
        String message = (String) params[1];

        try {
            IGClient client = IGClient.builder().username(BuildConfig.IG_USERNAME).password(BuildConfig.IG_PASSWORD).login();
            client.actions().timeline()
                    .uploadPhoto(imageFile, message)
                    .thenAccept(res -> System.out.println("Made new instagram post!"))
                    .exceptionally(tr -> {
                        System.out.println("Failed to make new instagram post: " + tr.getMessage());
                        return null;
                    })
                    .join();
        } catch (IGLoginException e) {
            e.printStackTrace();
        }
        return null;
    }

}
