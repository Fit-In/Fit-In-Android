package com.example.fitin_kotlin.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitin_kotlin.data.model.network.response.ResponseNewsList
import com.example.fitin_kotlin.databinding.ListItemBinding

class NewsAdapter(private val onClickListener: OnClickListener) : ListAdapter<ResponseNewsList, NewsAdapter.NewsListViewHolder>(DiffCallback), Filterable{

    var responseNewsList = listOf<ResponseNewsList>()
        set(value) {
            submitList(value)
            field = value
        }

    class NewsListViewHolder(private var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(responseNewsList: ResponseNewsList) {
            binding.newsProperty = responseNewsList
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val listItemNewsBinding: ListItemBinding = ListItemBinding.inflate(inflater, parent, false)
        return NewsListViewHolder(listItemNewsBinding)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val responseNewsList = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(responseNewsList)
        }
        holder.bind(responseNewsList)
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val resultList: MutableList<ResponseNewsList> = ArrayList()

                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    resultList.addAll(responseNewsList)
                } else {
                    for (newsList in responseNewsList) {
                        if (newsList.title!!.contains(charSearch)) {
                            resultList.add(newsList)
                        }
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = resultList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results?.values != null) {
                    submitList(results.values as List<ResponseNewsList>)
                }
            }

        }
    }


}