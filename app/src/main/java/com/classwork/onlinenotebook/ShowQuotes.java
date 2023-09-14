package com.classwork.onlinenotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.classwork.onlinenotebook.databinding.ActivityShowQuotesBinding;
import com.classwork.onlinenotebook.models.QuotesResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Callback;
import retrofit2.Response;

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
        binding.downloadtbn.setOnClickListener(v->{
            Toast.makeText(this, "Downloading...", Toast.LENGTH_SHORT).show();
        });
//copy to clipboard

        binding.copybtn.setOnClickListener(v->{

            String quotesdata=binding.quotesofbook.getText().toString().trim();
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("SB Rahman", quotesdata);

            clipboardManager.setPrimaryClip(clipData);


            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
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