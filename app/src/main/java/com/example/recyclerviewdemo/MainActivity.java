package com.example.recyclerviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.recyclerviewdemo.adapters.RecyclerViewAdapter;
import com.example.recyclerviewdemo.models.ShowImage;
import com.example.recyclerviewdemo.viewmodels.MainActivityViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    public RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Picsum");
        Log.d(TAG, "onCreate: started");

        relativeLayout = findViewById(R.id.progressLayout);
        recyclerView = findViewById(R.id.recyclerView);

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.init();

        mMainActivityViewModel.getShowImages().observe(this, new Observer<List<ShowImage>>() {
            @Override
            public void onChanged(List<ShowImage> showImages) {
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });


        initRecyclerView();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!recyclerView.canScrollVertically(1)) {
                    //Toast.makeText(MainActivity.this, "Last", Toast.LENGTH_LONG).show();
                    mMainActivityViewModel.loadMore();
                }
            }
        });

        mMainActivityViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(!aBoolean){
                    recyclerView.smoothScrollToPosition(mMainActivityViewModel.getShowImages().getValue().size()-1);
                }
            }
        });
    }

    public void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init RecyclerView");

        recyclerViewAdapter = new RecyclerViewAdapter(this, mMainActivityViewModel.getShowImages().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void hideProgressBar(){
        //relativeLayout.setVisibility(View.VISIBLE);
    }

    public void showProgressBar(){
        //relativeLayout.setVisibility(View.INVISIBLE);
    }
}
