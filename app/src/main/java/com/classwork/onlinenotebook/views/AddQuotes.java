package com.classwork.onlinenotebook.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.classwork.onlinenotebook.R;
import com.classwork.onlinenotebook.databinding.ActivityAddQuotesBinding;

public class AddQuotes extends AppCompatActivity {
    ActivityAddQuotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddQuotesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }
}