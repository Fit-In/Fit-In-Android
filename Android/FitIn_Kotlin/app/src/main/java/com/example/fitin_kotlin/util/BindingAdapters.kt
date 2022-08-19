package com.example.fitin_kotlin.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.fitin_kotlin.data.model.network.response.ResponseNewsList
import com.example.fitin_kotlin.data.model.network.response.ResponseRecruitmentList
import com.example.fitin_kotlin.ui.home.HomeNewsAdapter
import com.example.fitin_kotlin.ui.home.HomeRecruitmentAdapter
import com.example.fitin_kotlin.ui.news.NewsAdapter
import com.example.fitin_kotlin.ui.recruitment.RecruitmentAdapter

@BindingAdapter("newslistData")
fun bindNewsRecyclerView(recyclerView: RecyclerView, newsList: List<ResponseNewsList>?) {
    val adapter = recyclerView.adapter as NewsAdapter
    adapter.submitList(newsList)
}

@BindingAdapter("recruitmentlistData")
fun bindRecruitmentRecyclerView(recyclerView: RecyclerView, recruitmentList: List<ResponseRecruitmentList>?) {
    val adapter = recyclerView.adapter as RecruitmentAdapter
    adapter.submitList(recruitmentList)
}

@BindingAdapter("homeNewslistData")
fun bindHomeNewsRecyclerViews(recyclerView: RecyclerView, newsList: List<ResponseNewsList>?) {
    val adapter = recyclerView.adapter as HomeNewsAdapter
    adapter.submitList(newsList)
}

@BindingAdapter("homeRecruitmentlistData")
fun bindHomeRecruitmentRecyclerView(recyclerView: RecyclerView, recruitmentList: List<ResponseRecruitmentList>?) {
    val adapter = recyclerView.adapter as HomeRecruitmentAdapter
    adapter.submitList(recruitmentList)
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