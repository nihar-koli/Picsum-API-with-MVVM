package com.example.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recyclerviewdemo.R;

public class ShowImageActivity extends AppCompatActivity {

    private static final String TAG = "ShowImageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        setTitle("Image View");

        Log.d(TAG, "onCreate: started");

        getIntentExtras();
    }

    public void getIntentExtras(){
        Log.d(TAG, "getIntentExtras: checking incoming intents");

        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("image_author")) {
            Log.d(TAG, "getIntentExtras: found intent extras");
            Intent intent = getIntent();
            String image_url = intent.getStringExtra("image_url");
            String image_author = intent.getStringExtra("image_author");

            setImageView(image_url,image_author);
        }
    }

    public void setImageView(String image_url , String image_author){
        TextView textView = findViewById(R.id.authorTextView);
        ImageView imageView = findViewById(R.id.imageView);

        textView.setText("Image by "+image_author);

        Glide.with(this)
                .asBitmap()
                .load(image_url+".jpg")
                .centerCrop()
                .into(imageView);
    }
}
