package com.example.fitin_v2.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitin_v2.databinding.ListItemNewsBinding;
import com.example.fitin_v2.model.NewsListResponseDto;

import org.jetbrains.annotations.NotNull;

public class HomeAdapter extends ListAdapter<NewsListResponseDto, HomeAdapter.NewsListViewHolder> {

    private OnItemClickListener listener;

    protected HomeAdapter(@NonNull DiffUtil.ItemCallback<NewsListResponseDto> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public NewsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemNewsBinding binding = ListItemNewsBinding.inflate(inflater, parent, false);
        return new NewsListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListViewHolder holder, int position) {
        final NewsListResponseDto newsListResponseDto = this.getItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(newsListResponseDto);
            }
        });
        holder.bind(newsListResponseDto);
    }

    static class NewsListViewHolder extends RecyclerView.ViewHolder {
        private final ListItemNewsBinding binding;

        public NewsListViewHolder(@NonNull ListItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public final void bind(@NotNull NewsListResponseDto newsListResponseDto) {
            binding.setProperty(newsListResponseDto);
            binding.executePendingBindings();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(NewsListResponseDto newsListResponseDto);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
