package com.example.recyclerviewdemo.repositories;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.recyclerviewdemo.MainActivity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DownloadImageList extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            try{
                String result = "";
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while(data != -1){
                    char current =(char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    }

