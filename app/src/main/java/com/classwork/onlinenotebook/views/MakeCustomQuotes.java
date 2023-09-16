package com.classwork.onlinenotebook.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.classwork.onlinenotebook.databinding.ActivityMakeCustomQuotesBinding;

public class MakeCustomQuotes extends AppCompatActivity {
    ActivityMakeCustomQuotesBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMakeCustomQuotesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setTitle("Custom text");
        //  getSupportActionBar().setHomeButtonEnabled(true);


        binding.setcustomtesxtbtn.setOnClickListener(v -> {
            String userText = binding.editusertext.getText().toString();

            binding.usertextm.setText(userText);
            //  Toast.makeText(this, userText, Toast.LENGTH_SHORT).show();
            

        });


    }
}