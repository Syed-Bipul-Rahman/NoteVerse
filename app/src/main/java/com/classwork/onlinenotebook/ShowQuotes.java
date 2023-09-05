package com.classwork.onlinenotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.classwork.onlinenotebook.databinding.ActivityMainBinding;
import com.classwork.onlinenotebook.databinding.ActivityShowQuotesBinding;
import com.classwork.onlinenotebook.responses.QuotesResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowQuotes extends AppCompatActivity {
    ActivityShowQuotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowQuotesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        loadRandomQuote();

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRandomQuote();
            }
        });


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
}