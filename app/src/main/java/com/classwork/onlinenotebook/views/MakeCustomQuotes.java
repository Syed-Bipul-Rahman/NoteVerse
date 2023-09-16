package com.classwork.onlinenotebook.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.classwork.onlinenotebook.databinding.ActivityMakeCustomQuotesBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MakeCustomQuotes extends AppCompatActivity {
    ActivityMakeCustomQuotesBinding binding;
CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMakeCustomQuotesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setTitle("Custom text");
        //  getSupportActionBar().setHomeButtonEnabled(true);

        cardView= binding.cardViewuser;


        binding.setcustomtesxtbtn.setOnClickListener(v -> {
            String userText = binding.editusertext.getText().toString();

            binding.usertextm.setText(userText);
            //  Toast.makeText(this, userText, Toast.LENGTH_SHORT).show();


        });
        binding.downloadquotesbtn.setOnClickListener(v -> {

            Toast.makeText(this, "Downloading....", Toast.LENGTH_SHORT).show();


            saveImage();



        });

    }

    private void saveImage() {

        cardView.setDrawingCacheEnabled(true);
        cardView.buildDrawingCache();
        cardView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        //creating bitmap
        Bitmap bitmap = cardView.getDrawingCache();

        //calling save method
        save(bitmap);
    }

    private void save(Bitmap bitmap) {

        //for uniquename
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName = "IMG_" + timeStamp + ".png";

        // String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        // File file = new File(root + "/Downloads");
        File file = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //String fileName = "mysimple.jpg";


        File myfile = new File(file, fileName);

        if (myfile.exists()) {
            myfile.delete();
        }


        try {
            FileOutputStream fileOutputStream = new FileOutputStream(myfile);

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();


            cardView.setDrawingCacheEnabled(false);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Cannot save Image, error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}