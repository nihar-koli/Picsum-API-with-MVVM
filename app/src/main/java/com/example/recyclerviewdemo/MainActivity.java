package com.example.recyclerviewdemo;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.recyclerviewdemo.adapters.RecyclerViewAdapter;
import com.example.recyclerviewdemo.models.ShowImage;
import com.example.recyclerviewdemo.viewmodels.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private MainActivityViewModel mMainActivityViewModel;
    private FloatingActionButton nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Picsum");
        Log.d(TAG, "onCreate: started");

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setEnabled(true);

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.init();

        mMainActivityViewModel.getShowImages().observe(this, new Observer<List<ShowImage>>() {
            @Override
            public void onChanged(List<ShowImage> showImages) {
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });

        mMainActivityViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    showProgressBar();
                }else{
                    hideProgressBar();
                    recyclerViewAdapter.notifyDataSetChanged();

                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextButton.setEnabled(false);
                mMainActivityViewModel.loadMore();
                nextButton.setEnabled(true);
            }
        });

        initRecyclerView();
    }

    public void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init RecyclerView");

        recyclerViewAdapter = new RecyclerViewAdapter(this, mMainActivityViewModel.getShowImages().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    public void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }
}
