package com.example.fitin_v2.ui.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.fitin_v2.model.NewsListResponseDto;

public class HomeDiffUtil extends DiffUtil.ItemCallback<NewsListResponseDto> {

    @Override
    public boolean areItemsTheSame(@NonNull NewsListResponseDto oldItem, @NonNull NewsListResponseDto newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull NewsListResponseDto oldItem, @NonNull NewsListResponseDto newItem) {
        return oldItem.getId().equals(newItem.getId());
    }
}
