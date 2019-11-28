package com.example.recyclerviewdemo.repositories;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recyclerviewdemo.models.ShowImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShowImagesRepository {

    private static final String TAG = "ShowImagesRepository";
    public static int pageno = 0;

    private static ShowImagesRepository instance;
    private ArrayList<ShowImage> dataset = new ArrayList<>();

    public static ShowImagesRepository getInstance(){
        if(instance == null ){
            instance = new ShowImagesRepository();
        }
        return instance;
    }

    public MutableLiveData<List<ShowImage>> getShowImagesList(){
        pageno = pageno + 1;
//        setShowImages(pageno);

        MutableLiveData<List<ShowImage>> data = new MutableLiveData<List<ShowImage>>();
        data.setValue(dataset);
        return data;
    }

/*
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


 */
}


