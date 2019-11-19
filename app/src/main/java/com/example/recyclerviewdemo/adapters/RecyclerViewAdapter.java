package com.example.recyclerviewdemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.recyclerviewdemo.R;
import com.example.recyclerviewdemo.ShowImageActivity;
import com.example.recyclerviewdemo.models.ShowImage;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private List<ShowImage> mShowImages = new ArrayList<ShowImage>();
    private Context mContext;

    public RecyclerViewAdapter(Context context,List<ShowImage> showImages) {
        mShowImages = showImages;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_images,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mShowImages.get(position).getImageUrl()+".jpg")
                .centerCrop()
                .skipMemoryCache(false)
                .into(holder.imageView);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on -" + mShowImages.get(position).getImageAuthor());
                Intent intent = new Intent(mContext, ShowImageActivity.class);
                intent.putExtra("image_url",mShowImages.get(position).getImageUrl());
                intent.putExtra("image_author",mShowImages.get(position).getImageAuthor());
                mContext.startActivity(intent);
             }
        });

    }

    @Override
    public int getItemCount() {
        return mShowImages.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
