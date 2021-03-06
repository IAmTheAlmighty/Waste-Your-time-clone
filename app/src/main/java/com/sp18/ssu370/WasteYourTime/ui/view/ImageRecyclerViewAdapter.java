package com.sp18.ssu370.WasteYourTime.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.sp18.ssu370.WasteYourTime.model.ImgurImage;
import com.sp18.ssu370.WasteYourTime.model.ImgurImageList;
import com.sp18.ssu370.WasteYourTime.ui.activities.SingleItemActivity;
import com.sp18.ssu370.baseprojectapp.R;

import java.util.ArrayList;

public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.MyBigImgViewHolder> {

    private Context mContext;
    private ArrayList<ImgurImage> imgList;

    public ImageRecyclerViewAdapter(Context mContext, ArrayList<ImgurImage> imgList) {
        this.mContext = mContext;
        this.imgList = imgList;
    }

    @NonNull
    @Override
    public MyBigImgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.big_image_view, parent, false);

        return new MyBigImgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageRecyclerViewAdapter.MyBigImgViewHolder holder, final int position) {

        holder.bigImageTitle.setText(imgList.get(position).getName());
        holder.bigImageDescription.setText(imgList.get(position).getDescription());

        if(imgList.get(position).isAnimated()){
            GlideDrawableImageViewTarget gifLoad = new GlideDrawableImageViewTarget(holder.bigImgThumbnail);

            Glide.with(mContext)
                    .load(imgList.get(position).getUrl())
                    .into(gifLoad);
        }
        else{
            Glide.with(mContext)
                    .load(imgList.get(position).getUrl())
                    .into(holder.bigImgThumbnail);
        }

        holder.bigImgurImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SingleItemActivity.class);
                //pass data to image activity
                intent.putExtra("ImageURL", imgList.get(position).getUrl());
                intent.putExtra("Animated", imgList.get(position).isAnimated());
                //start the activity
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public static class MyBigImgViewHolder extends RecyclerView.ViewHolder{

        TextView bigImageTitle;
        TextView bigImageDescription;
        ImageView bigImgThumbnail;
        View bigImgurImageView;

        public MyBigImgViewHolder(View itemView){
            super(itemView);

            bigImageTitle = itemView.findViewById(R.id.big_image_view_title_id);
            bigImageDescription = itemView.findViewById(R.id.big_image_view_description_id);
            bigImgThumbnail = itemView.findViewById(R.id.big_image_view_thumbnail_id);
            bigImgurImageView = itemView.findViewById(R.id.big_image_view_id);
        }
    }
}
