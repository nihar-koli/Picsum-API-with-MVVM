package com.example.recyclerviewdemo.viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recyclerviewdemo.models.ShowImage;
import com.example.recyclerviewdemo.repositories.ShowImagesRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<ShowImage>> mShowImages;
    private ShowImagesRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if(mShowImages != null){
            return;
        }
        mRepo = ShowImagesRepository.getInstance();
        mShowImages = mRepo.getShowImagesList();
    }



    public void loadMore(){
        mIsUpdating.setValue(true);
        mRepo = ShowImagesRepository.getInstance();
        mShowImages = mRepo.getShowImagesList();
        mIsUpdating.setValue(false);
    }

    public LiveData<Boolean> getIsLoading(){
        return mIsUpdating;
    }

    public LiveData<List<ShowImage>> getShowImages(){
        return mShowImages;
    }
}
