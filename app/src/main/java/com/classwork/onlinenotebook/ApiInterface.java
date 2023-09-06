package com.classwork.onlinenotebook;
import com.classwork.onlinenotebook.models.QuotesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("random")
    Call<QuotesResponse> getQuotes();

}