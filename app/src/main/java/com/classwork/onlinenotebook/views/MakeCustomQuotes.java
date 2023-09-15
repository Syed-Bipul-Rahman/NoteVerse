package com.classwork.onlinenotebook.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.classwork.onlinenotebook.databinding.ActivityMakeCustomQuotesBinding;

public class MakeCustomQuotes extends AppCompatActivity {
ActivityMakeCustomQuotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMakeCustomQuotesBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
    }
}