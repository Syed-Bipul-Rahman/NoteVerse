package com.classwork.onlinenotebook.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.classwork.onlinenotebook.R;
import com.classwork.onlinenotebook.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {
ActivityUpdateBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityUpdateBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();

        setContentView(view);
    }
}