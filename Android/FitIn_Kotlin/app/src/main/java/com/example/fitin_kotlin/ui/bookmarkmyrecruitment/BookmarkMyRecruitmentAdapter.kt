package com.example.fitin_kotlin.ui.bookmarkmyrecruitment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitin_kotlin.data.model.network.response.ResponseSavedBookmark
import com.example.fitin_kotlin.databinding.ListItemMyRecruitmentBinding

class BookmarkMyRecruitmentAdapter(private val onClickListener: OnClickListener) : ListAdapter<ResponseSavedBookmark, BookmarkMyRecruitmentAdapter.BookmarkMyRecruitmentViewHolder>(DiffCallback) {

    class BookmarkMyRecruitmentViewHolder(private var binding: ListItemMyRecruitmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(responseSavedBookmark: ResponseSavedBookmark) {
            binding.newsProperty = responseSavedBookmark
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkMyRecruitmentAdapter.BookmarkMyRecruitmentViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val listItemMyRecruitmentBinding: ListItemMyRecruitmentBinding = ListItemMyRecruitmentBinding.inflate(inflater, parent, false)
        return BookmarkMyRecruitmentAdapter.BookmarkMyRecruitmentViewHolder(listItemMyRecruitmentBinding)
    }

    override fun onBindViewHolder(holder: BookmarkMyRecruitmentAdapter.BookmarkMyRecruitmentViewHolder, position: Int) {
        val responseMyRecruitmentList = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(responseMyRecruitmentList)
        }
        holder.bind(responseMyRecruitmentList)
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