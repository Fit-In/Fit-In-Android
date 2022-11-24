package com.example.fitin_kotlin.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitin_kotlin.data.model.network.response.ResponseBookmark
import com.example.fitin_kotlin.data.model.network.response.ResponseNewsList
import com.example.fitin_kotlin.databinding.ListItemBookmarkBinding

class BookmarkAdapter(private val onClickListener: OnClickListener): ListAdapter<ResponseBookmark, BookmarkAdapter.BookmarkListViewHolder>(DiffCallback), Filterable{

    var responseBookmarkList = listOf<ResponseBookmark>()
        set(value) {
            submitList(value)
            field = value
        }

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
    ): BookmarkListViewHolder {
        val inflater:LayoutInflater = LayoutInflater.from(parent.context)
        val listItemBookmarkBinding: ListItemBookmarkBinding = ListItemBookmarkBinding.inflate(inflater,parent,false)
        return BookmarkListViewHolder(listItemBookmarkBinding)
    }

    override fun onBindViewHolder(holder: BookmarkListViewHolder, position: Int) {
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val resultList: MutableList<ResponseBookmark> = ArrayList()

                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    resultList.addAll(responseBookmarkList)
                } else {
                    for (bookmarkList in responseBookmarkList) {
                        if (bookmarkList.bookmarkName!!.contains(charSearch)) {
                            resultList.add(bookmarkList)
                        }
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = resultList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results?.values != null) {
                    submitList(results.values as List<ResponseBookmark>)
                }
            }

        }
    }
}