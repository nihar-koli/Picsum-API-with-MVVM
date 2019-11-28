package com.example.recyclerviewdemo.requests;

import androidx.lifecycle.LiveData;

import com.example.recyclerviewdemo.models.ShowImage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PicsumApi {

    @GET("&limit=50")
    Call<ArrayList<ShowImage>> getImages();
}
