package com.example.recyclerviewdemo.requests;

import com.example.recyclerviewdemo.models.ShowImage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequestGenerator {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://picsum.photos/v2/list?page=")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    PicsumApi picsumApi = retrofit.create(PicsumApi.class);

    Call<ArrayList<ShowImage>> call = picsumApi.getImages();


}
