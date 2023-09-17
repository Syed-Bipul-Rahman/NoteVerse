package com.classwork.onlinenotebook.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.classwork.onlinenotebook.Adapters.BgAdapter;
import com.classwork.onlinenotebook.R;
import com.classwork.onlinenotebook.databinding.ActivityMakeCustomQuotesBinding;
import com.classwork.onlinenotebook.models.BGmodel;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MakeCustomQuotes extends AppCompatActivity {
    ActivityMakeCustomQuotesBinding binding;
    CardView cardView;

    ArrayList<BGmodel> arrayList;
    RecyclerView recyclerView;
    BgAdapter recyclerAdapter;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMakeCustomQuotesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setTitle("Custom text");
        //  getSupportActionBar().setHomeButtonEnabled(true);


        linearLayout = binding.marginlayout;
        SeekBar seekBarleft = binding.seekBarleft;
        SeekBar seekBarrigt = binding.seekbarringht;
        SeekBar seekBartop = binding.seekbartop;
        SeekBar seekBarbottom = binding.seekbarbottom;

        //change margin of textview
        seekBarleft.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                int marginLeft = (int) (progress * getResources().getDisplayMetrics().density); // Convert progress to pixels
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.setMargins(marginLeft, 0, 0, 0); // Set margins to move the TextView horizontally
                linearLayout.setLayoutParams(layoutParams);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarrigt.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                int marginRight = (int) (progress * getResources().getDisplayMetrics().density); // Convert progress to pixels
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.setMargins(0, 0, marginRight, 0); // Set margins to move the TextView horizontally
                linearLayout.setLayoutParams(layoutParams);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBartop.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                int margintop = (int) (progress * getResources().getDisplayMetrics().density); // Convert progress to pixels
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.setMargins(0, margintop, 0, 0); // Set margins to move the TextView horizontally
                linearLayout.setLayoutParams(layoutParams);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarbottom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                int marginbottom = (int) (progress * getResources().getDisplayMetrics().density); // Convert progress to pixels
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, marginbottom); // Set margins to move the TextView horizontally
                linearLayout.setLayoutParams(layoutParams);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        recyclerData();

        recyclerView = findViewById(R.id.bgrecyclerviewcustom);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerAdapter = new BgAdapter(this, arrayList);
        recyclerAdapter.setOnItemClickListener(new BgAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int imageResourceId = arrayList.get(position).getImage();
                binding.custombg.setBackgroundResource(imageResourceId);


            }
        });

        recyclerView.setAdapter(recyclerAdapter);

        cardView = binding.cardViewuser;


        binding.setcustomtesxtbtn.setOnClickListener(v -> {
            String userText = binding.editusertext.getText().toString();

            binding.usertextm.setText(userText);
            //  Toast.makeText(this, userText, Toast.LENGTH_SHORT).show();


        });
        binding.downloadquotesbtn.setOnClickListener(v -> {

            Toast.makeText(this, "Downloading....", Toast.LENGTH_SHORT).show();


            saveImage();


        });

        //drag textview


//change color of text

        binding.rradiogroupcustom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedbuttonid = radioGroup.getCheckedRadioButtonId();


                if (selectedbuttonid == binding.redradio.getId()) {
                    binding.usertextm.setTextColor(Color.RED);
                    binding.editusertext.setTextColor(Color.RED);

                } else if (selectedbuttonid == binding.greenradio.getId()) {
                    binding.usertextm.setTextColor(Color.GREEN);
                    binding.editusertext.setTextColor(Color.GREEN);

                } else if (selectedbuttonid == binding.blueradio.getId()) {
                    binding.usertextm.setTextColor(Color.BLUE);
                    binding.editusertext.setTextColor(Color.BLUE);

                } else if (selectedbuttonid == binding.blackradio.getId()) {
                    binding.usertextm.setTextColor(Color.BLACK);
                    binding.editusertext.setTextColor(Color.BLACK);

                } else if (selectedbuttonid == binding.whiteradio.getId()) {
                    binding.usertextm.setTextColor(Color.WHITE);
                    binding.editusertext.setTextColor(Color.BLACK);

                }

            }
        });


//        binding.showseekbars.setOnClickListener(v->{
//
//            binding.editusertext.setVisibility(view.GONE);
//            binding.setmarginlayoutvair.setVisibility(view.VISIBLE);
//        });

        binding.showseekbars.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                binding.editusertext.setVisibility(b ? View.GONE : View.VISIBLE);
                binding.setmarginlayoutvair.setVisibility(b ? View.VISIBLE : View.GONE);
            }
        });

    }

    private void recyclerData() {
        arrayList = new ArrayList<>();

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
}