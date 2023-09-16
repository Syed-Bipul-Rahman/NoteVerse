package com.classwork.onlinenotebook.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.classwork.onlinenotebook.databinding.ActivityShowBackgroundImagesBinding;

public class ShowBackgroundImages extends AppCompatActivity {
    ActivityShowBackgroundImagesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShowBackgroundImagesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);







    }
}