package com.example.fitin_kotlin.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitin_kotlin.data.model.network.response.ResponseBookmark
import com.example.fitin_kotlin.databinding.ListItemBookmarkBinding

class BookmarkAdapter(private val onClickListener: OnClickListener): ListAdapter<ResponseBookmark, BookmarkAdapter.BookmarkListViewHolder>(DiffCallback){


    class BookmarkListViewHolder(private var binding: ListItemBookmarkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(responseBookmark: ResponseBookmark) {
            binding.bookmarkProperty = responseBookmark
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (responseBookmark: ResponseBookmark) -> Unit) {
        fun onClick(responseBookmark: ResponseBookmark) = clickListener(responseBookmark)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkAdapter.BookmarkListViewHolder {
        val inflater:LayoutInflater = LayoutInflater.from(parent.context)
        val listItemBookmarkBinding: ListItemBookmarkBinding = ListItemBookmarkBinding.inflate(inflater,parent,false)
        return BookmarkListViewHolder(listItemBookmarkBinding)
    }

    override fun onBindViewHolder(holder: BookmarkAdapter.BookmarkListViewHolder, position: Int) {
        val responseBookmark = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(responseBookmark)
        }
        holder.bind(responseBookmark)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<ResponseBookmark>() {
        override fun areItemsTheSame(
            oldItem: ResponseBookmark,
            newItem: ResponseBookmark
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ResponseBookmark,
            newItem: ResponseBookmark
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }
}