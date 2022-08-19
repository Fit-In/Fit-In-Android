package com.example.fitin_kotlin.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitin_kotlin.data.model.network.response.ResponseNewsList
import com.example.fitin_kotlin.databinding.ListItemNewsBinding
import kotlin.math.min

class HomeNewsAdapter(private val onClickListener: OnClickListener) : ListAdapter<ResponseNewsList, HomeNewsAdapter.NewsListViewHolder>(DiffCallback){

    class NewsListViewHolder(private var binding: ListItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(responseNewsList: ResponseNewsList) {
            binding.newsProperty = responseNewsList
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val listItemNewsBinding: ListItemNewsBinding = ListItemNewsBinding.inflate(inflater, parent, false)
        return NewsListViewHolder(listItemNewsBinding)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val responseNewsList = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(responseNewsList)
        }
        holder.bind(responseNewsList)
    }

    override fun getItemCount(): Int {
        val limit = 3
        return min(super.getItemCount(), limit)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ResponseNewsList>() {
        override fun areItemsTheSame(
            oldItem: ResponseNewsList,
            newItem: ResponseNewsList
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ResponseNewsList,
            newItem: ResponseNewsList
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class OnClickListener(val clickListener: (responseNewsList:ResponseNewsList) -> Unit) {
        fun onClick(responseNewsList: ResponseNewsList) = clickListener(responseNewsList)
    }

}