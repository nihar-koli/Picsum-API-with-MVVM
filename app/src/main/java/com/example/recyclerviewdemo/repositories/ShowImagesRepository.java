package com.example.recyclerviewdemo.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.recyclerviewdemo.models.ShowImage;
import com.example.recyclerviewdemo.requests.PicsumApi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowImagesRepository {

    private static final String TAG = "ShowImagesRepository";
    public static int pageno = 0;

    private static ShowImagesRepository instance;
    private ArrayList<ShowImage> dataset = new ArrayList<>();
    MutableLiveData<List<ShowImage>> data;

    public static ShowImagesRepository getInstance(){
        if(instance == null ){
            instance = new ShowImagesRepository();
        }
        return instance;
    }

    public MutableLiveData<List<ShowImage>> getShowImagesList(){
        pageno = pageno + 1;
        setShowImages(pageno);
        MutableLiveData<List<ShowImage>> data = new MutableLiveData<List<ShowImage>>();
        data.setValue(dataset);
        return data;


/*        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://picsum.photos/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PicsumApi picsumApi = retrofit.create(PicsumApi.class);

        Call<List<ShowImage>> call = picsumApi.getImages();

        call.enqueue(new Callback<List<ShowImage>>() {
            @Override
            public void onResponse(Call<List<ShowImage>> call, Response<List<ShowImage>> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: failure http 404" + response.message());
                }

                data = new MutableLiveData<List<ShowImage>>();
                data.setValue(dataset);
            }

            @Override
            public void onFailure(Call<List<ShowImage>> call, Throwable t) {

            }
        });
        return data;
*/
    }

    private void setShowImages(int pageno) {

        DownloadImageList task = new DownloadImageList();
        String result;

        try {

            result = task.execute("https://picsum.photos/v2/list?page="+String.valueOf(pageno) +"&limit=50").get();
            Log.d(TAG, "initImageBitmaps: download done");

            JSONArray array = new JSONArray(result);

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String url = jsonObject.getString("download_url");
                String author = jsonObject.getString("author");
                dataset.add(new ShowImage(author, url));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "initImageBitmapsError: " + e.toString());
        }

    }
}


