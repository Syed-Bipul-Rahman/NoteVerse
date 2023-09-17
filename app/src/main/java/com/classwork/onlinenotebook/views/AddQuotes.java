package com.classwork.onlinenotebook.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.classwork.onlinenotebook.ApiClient;
import com.classwork.onlinenotebook.ApiInterface;
import com.classwork.onlinenotebook.R;
import com.classwork.onlinenotebook.databinding.ActivityAddQuotesBinding;
import com.classwork.onlinenotebook.responses.AddnoteResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddQuotes extends AppCompatActivity {
    ActivityAddQuotesBinding binding;
    Retrofit retrofit = ApiClient.getClient();
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddQuotesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().setTitle("Add Quotes");
        apiInterface = retrofit.create(ApiInterface.class);

//get todays date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String todaysdate = simpleDateFormat.format(calendar.getTime()).toString();


        //send data to server when clicked on button add
        binding.buttonaddquotel.setOnClickListener(v -> {
            String quote = binding.quotetext.getText().toString();
            if (quote.isEmpty()) {


                binding.quotetext.setError("Please enter a quote");
                binding.quotetext.requestFocus();

            } else {
                String date = todaysdate;
                //send data to server
                String color="3";

                Toast.makeText(this, "Calling api...", Toast.LENGTH_SHORT).show();

                sendDataToServer(quote, date,color);

            }


        });

    }

    private void sendDataToServer(String quote, String date,String color) {
        apiInterface.addNote(quote, date,color).enqueue(new Callback<AddnoteResponse>() {
            @Override
            public void onResponse(Call<AddnoteResponse> call, Response<AddnoteResponse> response) {

                try {
                    if (response.body().getStatus().equals("1")) {
                        // Toast.makeText(AddQuotes.this, "Data Successfully inserted", Toast.LENGTH_SHORT).show();

                        AlertDialog.Builder builder = new AlertDialog.Builder(AddQuotes.this);
                        builder.setIcon(R.drawable.ic_baseline_check_circle_24);
                        builder.setTitle("Success");
                        builder.setMessage("Data Successfully inserted");
                        builder.setPositiveButton("Home", (dialog, which) -> {
                            dialog.dismiss();
                            finish();
                        });
                        builder.setNegativeButton("Add More", (dialog, which) -> {
                            dialog.dismiss();
                            binding.quotetext.setText("");
                        });

                        builder.show();


                    } else {
                        Toast.makeText(AddQuotes.this, "Data insertion failed!🙂", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                    Toast.makeText(AddQuotes.this, "Something went wrong bro😛", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<AddnoteResponse> call, Throwable t) {
                Log.e("failer", t.getLocalizedMessage());
                Toast.makeText(AddQuotes.this, "Something went wrong😥", Toast.LENGTH_SHORT).show();
            }
        });

    }
}