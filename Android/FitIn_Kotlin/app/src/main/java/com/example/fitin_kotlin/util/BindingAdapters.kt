package com.example.fitin_kotlin.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.fitin_kotlin.data.model.network.response.ResponseNewsList
import com.example.fitin_kotlin.ui.home.HomeAdapter
import com.example.fitin_kotlin.ui.news.NewsAdapter

@BindingAdapter("newslistData")
fun bindNewsRecyclerView(recyclerView: RecyclerView, newsList: List<ResponseNewsList>?) {
    val adapter = recyclerView.adapter as NewsAdapter
    adapter.submitList(newsList)
}

@BindingAdapter("homeNewslistData")
fun bindHomeNewsRecyclerViews(recyclerView: RecyclerView, newsList: List<ResponseNewsList>?) {
    val adapter = recyclerView.adapter as HomeAdapter
    adapter.submitList(newsList)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        // URL 값을 Uri 객체로 변환함
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        // Glide를 통해 imageView에 load한 이미지를 넣음
        Glide.with(imageView.context)
            .load(imgUri)
            .transform(CenterInside(),RoundedCorners(24))
            .into(imageView)
    }
}