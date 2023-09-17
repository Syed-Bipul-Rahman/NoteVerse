package com.classwork.onlinenotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.classwork.onlinenotebook.Adapters.BgAdapter;
import com.classwork.onlinenotebook.databinding.ActivityShowQuotesBinding;
import com.classwork.onlinenotebook.models.BGmodel;
import com.classwork.onlinenotebook.models.QuotesResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    private boolean rotete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowQuotesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        FloatingActionButton fabadd, fabcangebg, fabchangecolor;

        fabadd = binding.fabadd;
        fabcangebg = binding.changebgbutton;
        fabchangecolor = binding.changecolorbtn;

        initshowOut(fabcangebg);
        initshowOut(fabchangecolor);

        fabadd.setOnClickListener(v -> {

            rotete = rotatefab(v, !rotete);
            if (rotete) {
                showIn(fabchangecolor);
                showIn(fabcangebg);
            } else {
                showOut(fabchangecolor);
                showOut(fabcangebg);
            }

        });


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


        binding.rradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedbuttonid = radioGroup.getCheckedRadioButtonId();


                if (selectedbuttonid == binding.redradio.getId()) {
                    binding.quotesofbook.setTextColor(Color.RED);

                } else if (selectedbuttonid == binding.greenradio.getId()) {
                    binding.quotesofbook.setTextColor(Color.GREEN);

                } else if (selectedbuttonid == binding.blueradio.getId()) {
                    binding.quotesofbook.setTextColor(Color.BLUE);

                } else if (selectedbuttonid == binding.blackradio.getId()) {
                    binding.quotesofbook.setTextColor(Color.BLACK);

                } else if (selectedbuttonid == binding.whiteradio.getId()) {
                    binding.quotesofbook.setTextColor(Color.WHITE);

                }

            }
        });


    }

    private void recyclerData() {

        arrayList = new ArrayList<>();
//adding backgrounds
        arrayList.add(new BGmodel(R.drawable.on));
        arrayList.add(new BGmodel(R.drawable.abstrack));
        arrayList.add(new BGmodel(R.drawable.abstrackbdbackgroud));
        arrayList.add(new BGmodel(R.drawable.abstrackbg));
        arrayList.add(new BGmodel(R.drawable.abstracktw));
        arrayList.add(new BGmodel(R.drawable.badckraund));
        arrayList.add(new BGmodel(R.drawable.balkframe));
        arrayList.add(new BGmodel(R.drawable.bgnoedlfld));
        arrayList.add(new BGmodel(R.drawable.bgnoweee));
        arrayList.add(new BGmodel(R.drawable.blackwhitebox));
        arrayList.add(new BGmodel(R.drawable.blackwhotes));
        arrayList.add(new BGmodel(R.drawable.blackwite));
        arrayList.add(new BGmodel(R.drawable.blckwhitetwoline));
        arrayList.add(new BGmodel(R.drawable.boxwithredwhite));
        arrayList.add(new BGmodel(R.drawable.card));
        arrayList.add(new BGmodel(R.drawable.cardd));
        arrayList.add(new BGmodel(R.drawable.dropneon));
        arrayList.add(new BGmodel(R.drawable.facebookback));
        arrayList.add(new BGmodel(R.drawable.fancy));
        arrayList.add(new BGmodel(R.drawable.flowerboxsxx));
        arrayList.add(new BGmodel(R.drawable.flowerframe));
        arrayList.add(new BGmodel(R.drawable.flowertwoline));
        arrayList.add(new BGmodel(R.drawable.fraeme));
        arrayList.add(new BGmodel(R.drawable.frame));
        arrayList.add(new BGmodel(R.drawable.frameneon));
        arrayList.add(new BGmodel(R.drawable.frameneoneee));
        arrayList.add(new BGmodel(R.drawable.framenewon));
        arrayList.add(new BGmodel(R.drawable.framewithflower));
        arrayList.add(new BGmodel(R.drawable.freamess));
        arrayList.add(new BGmodel(R.drawable.goldenboxsq));
        arrayList.add(new BGmodel(R.drawable.goldenframe));
        arrayList.add(new BGmodel(R.drawable.goldentwoline));
        arrayList.add(new BGmodel(R.drawable.greenframe));
        arrayList.add(new BGmodel(R.drawable.imageframe));
        arrayList.add(new BGmodel(R.drawable.lightframedd));
        arrayList.add(new BGmodel(R.drawable.love));
        arrayList.add(new BGmodel(R.drawable.loveframe));
        arrayList.add(new BGmodel(R.drawable.lovepinkskyblue));
        arrayList.add(new BGmodel(R.drawable.lovesss));
        arrayList.add(new BGmodel(R.drawable.loveto));
        arrayList.add(new BGmodel(R.drawable.moonsho));
        arrayList.add(new BGmodel(R.drawable.neonboxxxxx));
        arrayList.add(new BGmodel(R.drawable.neonebggg));
        arrayList.add(new BGmodel(R.drawable.neonetwoline));
        arrayList.add(new BGmodel(R.drawable.newo));
        arrayList.add(new BGmodel(R.drawable.noeneframeees));
        arrayList.add(new BGmodel(R.drawable.noenetwolinesss));
        arrayList.add(new BGmodel(R.drawable.noeoneboxss));
        arrayList.add(new BGmodel(R.drawable.nwonebackgro));
        arrayList.add(new BGmodel(R.drawable.onelinecard));
        arrayList.add(new BGmodel(R.drawable.onelined));
        arrayList.add(new BGmodel(R.drawable.pinkbg));
        arrayList.add(new BGmodel(R.drawable.quotes));
        arrayList.add(new BGmodel(R.drawable.quotestw));
        arrayList.add(new BGmodel(R.drawable.redfreame));
        arrayList.add(new BGmodel(R.drawable.redwhitequotes));
        arrayList.add(new BGmodel(R.drawable.sceinceframessbg));
        arrayList.add(new BGmodel(R.drawable.skybluebackgroud));
        arrayList.add(new BGmodel(R.drawable.smokeabstrack));
        arrayList.add(new BGmodel(R.drawable.smokecircle));
        arrayList.add(new BGmodel(R.drawable.wallframess));
        arrayList.add(new BGmodel(R.drawable.white));
        arrayList.add(new BGmodel(R.drawable.whitefreamess));
        arrayList.add(new BGmodel(R.drawable.whitequotess));
        arrayList.add(new BGmodel(R.drawable.whiteskyblueeee));
        arrayList.add(new BGmodel(R.drawable.yellowframe));
        arrayList.add(new BGmodel(R.drawable.yellowquotes));
        arrayList.add(new BGmodel(R.drawable.yellowquotesww));
        arrayList.add(new BGmodel(R.drawable.yellowtwoline));

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

    public static void initshowOut(final View v) {
        v.setVisibility(View.GONE);
        v.setTranslationY(v.getHeight());

        v.setAlpha(0f);

    }


    public static boolean rotatefab(final View v, boolean rotate) {
        v.animate().setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                    }
                }).rotation(rotate ? 135f : 0f);
        return rotate;
    }


    public static void showIn(final View v) {
        v.setVisibility(View.VISIBLE);
        v.setAlpha(0f);
        v.setTranslationY(v.getHeight());
        v.animate()
                .setDuration(200)
                .translationY(0)
                // .translationY(v.getHeight())
                //.alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {

                        super.onAnimationEnd(animation);
                    }
                }).alpha(1f)
                .start();

    }


    public static void showOut(final View v) {
        v.setVisibility(View.VISIBLE);
        v.setAlpha(1f);
        v.setTranslationY(0);
        v.animate()
                .setDuration(200)
                .translationY(v.getHeight())
                // .translationY(v.getHeight())
                //.alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        v.setVisibility(View.GONE);
                        super.onAnimationEnd(animation);
                    }
                }).alpha(0f)
                .start();

    }

}