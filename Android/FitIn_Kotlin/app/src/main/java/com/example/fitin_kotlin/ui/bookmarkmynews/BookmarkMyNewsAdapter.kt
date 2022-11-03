package com.example.fitin_kotlin.ui.bookmarkmynews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitin_kotlin.data.model.network.response.ResponseSavedBookmark
import com.example.fitin_kotlin.databinding.ListItemMyNewsBinding

class BookmarkMyNewsAdapter(private val onClickListener: OnClickListener) : ListAdapter<ResponseSavedBookmark, BookmarkMyNewsAdapter.BookmarkMyNewsListViewHolder>(DiffCallback) {

    class BookmarkMyNewsListViewHolder(private var binding: ListItemMyNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(responseSavedBookmark: ResponseSavedBookmark) {
            binding.newsProperty = responseSavedBookmark
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkMyNewsListViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val listItemMyNewsBinding: ListItemMyNewsBinding = ListItemMyNewsBinding.inflate(inflater, parent, false)
        return BookmarkMyNewsListViewHolder(listItemMyNewsBinding)
    }

    override fun onBindViewHolder(holder: BookmarkMyNewsListViewHolder, position: Int) {
        val responseMyNewsList = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(responseMyNewsList)
        }
        holder.bind(responseMyNewsList)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ResponseSavedBookmark>() {
        override fun areItemsTheSame(
            oldItem: ResponseSavedBookmark,
            newItem: ResponseSavedBookmark
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ResponseSavedBookmark,
            newItem: ResponseSavedBookmark
        ): Boolean {
            return oldItem.bookmarkId == newItem.bookmarkId
        }

    }

    class OnClickListener(val clickListener: (responseSavedBookmark: ResponseSavedBookmark) -> Unit) {
        fun onClick(responseSavedBookmark: ResponseSavedBookmark) = clickListener(responseSavedBookmark)
    }
}