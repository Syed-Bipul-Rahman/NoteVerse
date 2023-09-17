package com.classwork.onlinenotebook.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.classwork.onlinenotebook.ApiClient;
import com.classwork.onlinenotebook.ApiInterface;
import com.classwork.onlinenotebook.R;
import com.classwork.onlinenotebook.databinding.ActivityAddQuotesBinding;
import com.classwork.onlinenotebook.databinding.ActivityUpdateBinding;
import com.classwork.onlinenotebook.responses.AddnoteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateActivity extends AppCompatActivity {
    ActivityUpdateBinding binding;
    Retrofit retrofit = ApiClient.getClient();
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        getSupportActionBar().setTitle("Update Note");
        apiInterface = retrofit.create(ApiInterface.class);


        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        int id = intent.getIntExtra("id", 0);

        String convertedid = String.valueOf(id);
        binding.updatetext.setText(text);


        binding.updatebtn.setOnClickListener(v -> {
            String updatetextbb = binding.updatetext.getText().toString().trim();

            if (updatetextbb.isEmpty()) {
                binding.updatetext.setError("Please enter a note");
                binding.updatetext.requestFocus();
            } else {
                Toast.makeText(this, "Calling api...", Toast.LENGTH_SHORT).show();

                sendDataToServer(convertedid, updatetextbb);
            }

        });

    }

    private void sendDataToServer(String convertedid, String updatetextbb) {

        apiInterface.updateNote(convertedid,updatetextbb).enqueue(new Callback<AddnoteResponse>() {
            @Override
            public void onResponse(Call<AddnoteResponse> call, Response<AddnoteResponse> response) {

                try {
                    if (response.body().getStatus().equals("1")) {
                        // Toast.makeText(AddQuotes.this, "Data Successfully inserted", Toast.LENGTH_SHORT).show();


                        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                        builder.setIcon(R.drawable.ic_baseline_check_circle_24);
                        builder.setTitle("Success");
                        builder.setMessage("Data Successfully updated!🙂");
                        builder.setPositiveButton("ok", (dialog, which) -> {
                            dialog.dismiss();
                            finish();
                        });


                        builder.show();


                    } else {
                        Toast.makeText(UpdateActivity.this, "Data update failed!🙂", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                    Toast.makeText(UpdateActivity.this, "Something went wrong bro😛", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<AddnoteResponse> call, Throwable t) {
                Log.e("failer", t.getLocalizedMessage());
                Toast.makeText(UpdateActivity.this, "Something went wrong😥", Toast.LENGTH_SHORT).show();
            }
        });



    }
}