package com.classwork.onlinenotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.classwork.onlinenotebook.Adapters.BgAdapter;
import com.classwork.onlinenotebook.databinding.ActivityShowQuotesBinding;
import com.classwork.onlinenotebook.models.BGmodel;
import com.classwork.onlinenotebook.models.QuotesResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowQuotes extends AppCompatActivity {
    ActivityShowQuotesBinding binding;
    CardView cardView;
    //  private static final int MY_PERMISSIONS_REQUEST_MANAGE_EXTERNAL_STORAGE = 1;
    ArrayList<BGmodel> arrayList;
    RecyclerView recyclerView;
    BgAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowQuotesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        recyclerData();

        recyclerView = findViewById(R.id.bgrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerAdapter = new BgAdapter(this, arrayList);


        recyclerAdapter.setOnItemClickListener(new BgAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int imageResourceId = arrayList.get(position).getImage();
                binding.setbglinear.setBackgroundResource(imageResourceId);


            }
        });

        recyclerView.setAdapter(recyclerAdapter);


        cardView = binding.cardView;
        loadRandomQuote();

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRandomQuote();
            }
        });
        binding.downloadtbn.setOnClickListener(v -> {
            Toast.makeText(this, "Downloading...", Toast.LENGTH_SHORT).show();

            saveImage();


        });
//copy to clipboard

        binding.copybtn.setOnClickListener(v -> {

            String quotesdata = binding.quotesofbook.getText().toString().trim();
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("SB Rahman", quotesdata);

            clipboardManager.setPrimaryClip(clipData);


            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
        });
        binding.changebgbutton.setOnClickListener(view1 -> {
            Toast.makeText(this, "Select bg to set", Toast.LENGTH_SHORT).show();
            binding.rradiogroup.setVisibility(View.GONE);
            binding.bgrecyclerview.setVisibility(View.VISIBLE);


        });
        binding.changecolorbtn.setOnClickListener(view1 -> {
            binding.bgrecyclerview.setVisibility(View.GONE);
            binding.rradiogroup.setVisibility(View.VISIBLE);

            //  Toast.makeText(this, "changing color", Toast.LENGTH_SHORT).show();
        });


        //  binding.downloadtbn.setOnClickListener(v -> {
//            if (ContextCompat.checkSelfPermission(ShowQuotes.this, Manifest.permission.MANAGE_EXTERNAL_STORAGE)
//                    != PackageManager.PERMISSION_GRANTED) {
//                // Request the permission
//                ActivityCompat.requestPermissions(ShowQuotes.this,
//                        new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},
//                        MY_PERMISSIONS_REQUEST_MANAGE_EXTERNAL_STORAGE);
//            } else {
//                // Permission is already granted, proceed to save the image
//                saveImage();
//            }


        //     });


    }

    private void recyclerData() {

        arrayList = new ArrayList<>();

        arrayList.add(new BGmodel(R.drawable.ic_splash));
        arrayList.add(new BGmodel(R.drawable.on));
        arrayList.add(new BGmodel(R.drawable.one));
        arrayList.add(new BGmodel(R.drawable.onee));
        arrayList.add(new BGmodel(R.drawable.oneee));
        arrayList.add(new BGmodel(R.drawable.oneeeee));
        arrayList.add(new BGmodel(R.drawable.oneeeeee));

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

//    private void saveImage() {
//
//        Bitmap cardBitmap = captureView(cardView);
//
//        if (cardBitmap != null) {
//            boolean isSaved = saveBitmapToStorage(cardBitmap);
//            if (isSaved) {
//                Toast.makeText(ShowQuotes.this, "Image saved successfully", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(ShowQuotes.this, "Failed to save image", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private boolean saveBitmapToStorage(Bitmap bitmap) {
//        // Check if external storage is available for writing
//        if (isExternalStorageWritable()) {
//            // Create a directory to save the images (you can customize this path)
//            File directory = new File(Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_PICTURES), "YourAppImages");
//
//            if (!directory.exists()) {
//                if (!directory.mkdirs()) {
//                    return false; // Failed to create the directory
//                }
//            }
//
//            // Generate a unique file name using a timestamp
//            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
//            String fileName = "IMG_" + timeStamp + ".png";
//
//            // Create the file to save the image
//            File imageFile = new File(directory, fileName);
//
//            try {
//                FileOutputStream fos = new FileOutputStream(imageFile);
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
//                fos.close();
//                return true; // Image saved successfully
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return false; // External storage is not writable or an error occurred
//    }
//
//    // Check if external storage is available for writing
//    private boolean isExternalStorageWritable() {
//        String state = Environment.getExternalStorageState();
//        return Environment.MEDIA_MOUNTED.equals(state);
//    }
//
//    private Bitmap captureView(View view) {
//        view.setDrawingCacheEnabled(true);
//        view.buildDrawingCache();
//        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
//        view.setDrawingCacheEnabled(false);
//        return bitmap;
//    }

    private void loadRandomQuote() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.quotable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<QuotesResponse> call = apiInterface.getQuotes();

        call.enqueue(new Callback<QuotesResponse>() {
            @Override
            public void onResponse(Call<QuotesResponse> call, Response<QuotesResponse> response) {
                if (response.isSuccessful()) {
                    QuotesResponse apiResponse = response.body();
                    binding.quotesofbook.setText(apiResponse.getContent());
                    binding.authorofbook.setText("- " + apiResponse.getAuthor());
                }
            }

            @Override
            public void onFailure(Call<QuotesResponse> call, Throwable t) {
                Toast.makeText(ShowQuotes.this, "failed to connect with server", Toast.LENGTH_SHORT).show();
            }
        });
    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == MY_PERMISSIONS_REQUEST_MANAGE_EXTERNAL_STORAGE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, proceed to save the image
//                saveImage();
//            } else {
//                // Permission denied, show a message to the user
//                Toast.makeText(this, "Permission denied. Cannot save image.", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//
//    }
}