package com.classwork.onlinenotebook;
import com.classwork.onlinenotebook.models.AllNotes;
import com.classwork.onlinenotebook.models.QuotesResponse;
import com.classwork.onlinenotebook.responses.AddResponse;
import com.classwork.onlinenotebook.responses.AddnoteResponse;
import com.classwork.onlinenotebook.responses.GetNotesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("random")
    Call<QuotesResponse> getQuotes();

    @GET("shownotes.php")
    Call<GetNotesResponse> allNotes();

    @FormUrlEncoded
    @POST("addnote.php")
    Call<AddnoteResponse> addNote(@Field("notes") String notes,
                                  @Field("updateat") String date,
                                  @Field("color") String color);



}