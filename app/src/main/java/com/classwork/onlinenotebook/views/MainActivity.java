package com.classwork.onlinenotebook.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.classwork.onlinenotebook.ShowQuotes;
import com.classwork.onlinenotebook.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.showQuotes.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ShowQuotes.class));
        });
binding.addnotes.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddQuotes.class));
        });

    }
}