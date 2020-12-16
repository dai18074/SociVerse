package com.example.socialmanager.ui.accounts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.socialmanager.R;

public class AccountsFragment extends Fragment {

    private AccountsViewModel accountsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountsViewModel =
                new ViewModelProvider(this).get(AccountsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_accounts, container, false);
        final TextView textView = root.findViewById(R.id.text_accounts);
        accountsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}