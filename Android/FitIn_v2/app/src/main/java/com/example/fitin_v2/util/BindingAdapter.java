package com.example.fitin_v2.util;

import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fitin_v2.model.NewsListResponseDto;
import com.example.fitin_v2.ui.home.HomeAdapter;

import java.util.List;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("listData")
    public static void BindData(RecyclerView recyclerView, List<NewsListResponseDto> news) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        HomeAdapter adapters = (HomeAdapter) adapter;
        adapters.submitList(news);
    }

    @androidx.databinding.BindingAdapter("imageUrl")
    public static void bindImage(ImageView imageView, String imgUrl) {
//        Uri uri = Uri.parse(imgUrl);
//        Uri imgUri = uri.buildUpon().scheme("https").build();
        Glide.with(imageView.getContext())
                .load(imgUrl)
                .into(imageView);
    }
}
