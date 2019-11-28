package com.example.recyclerviewdemo.requests;

import com.example.recyclerviewdemo.models.ShowImage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PicsumApi {

    @GET()
    Call<List<ShowImage>> getImages();
}

