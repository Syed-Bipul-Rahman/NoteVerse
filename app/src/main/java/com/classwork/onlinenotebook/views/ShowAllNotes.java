package com.classwork.onlinenotebook.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.classwork.onlinenotebook.Adapters.RecyclerAdapters;
import com.classwork.onlinenotebook.ApiClient;
import com.classwork.onlinenotebook.ApiInterface;
import com.classwork.onlinenotebook.R;
import com.classwork.onlinenotebook.databinding.ActivityShowAllNotesBinding;
import com.classwork.onlinenotebook.models.AllNotes;
import com.classwork.onlinenotebook.responses.GetNotesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShowAllNotes extends AppCompatActivity {
    ActivityShowAllNotesBinding binding;
    RecyclerView recyclerView;
    RecyclerAdapters dataAdapter;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowAllNotesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initializing();
        getdata();


    }

    private void getdata() {


        apiInterface.allNotes().enqueue(new Callback<GetNotesResponse>() {
            @Override
            public void onResponse(Call<GetNotesResponse> call, Response<GetNotesResponse> response) {
                try {
                    if (response != null) {
                        if (response.body().getStatus().equals("1")) {

                            setDataAdapter(response.body().getData());

                        } else {
                            Toast.makeText(ShowAllNotes.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                } catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                    Toast.makeText(ShowAllNotes.this, "Something went wrong😥", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<GetNotesResponse> call, Throwable t) {
                Log.e("failer", t.getLocalizedMessage());
                Toast.makeText(ShowAllNotes.this, "Something went wrong😥", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void initializing() {
        recyclerView = binding.recylerveiwallnotes;

        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);


    }

    private void setDataAdapter(List<AllNotes> dataModels) {
        dataAdapter = new RecyclerAdapters(this, dataModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(dataAdapter);
    }
}
