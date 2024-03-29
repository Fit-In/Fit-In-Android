package com.example.fitin_kotlin.ui.recruitment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitin_kotlin.data.model.network.response.ResponseRecruitmentList
import com.example.fitin_kotlin.databinding.ListItemRecruitmentBinding

class RecruitmentAdapter(private val onClickListener: OnClickListener) : ListAdapter<ResponseRecruitmentList, RecruitmentAdapter.RecruitmentListViewHolder>(DiffCallback), Filterable {

    var responseRecruitmentList = listOf<ResponseRecruitmentList>()
        set(value) {
            submitList(value)
            field = value
        }

    class RecruitmentListViewHolder(private var binding: ListItemRecruitmentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(responseRecruitmentList: ResponseRecruitmentList) {
            binding.recruitmentProperty = responseRecruitmentList
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecruitmentListViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val listItemRecruitmentBinding: ListItemRecruitmentBinding = ListItemRecruitmentBinding.inflate(inflater, parent, false)
        return RecruitmentListViewHolder(listItemRecruitmentBinding)
    }

    override fun onBindViewHolder(holder: RecruitmentListViewHolder, position: Int) {
        val responseRecruitmentList = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(responseRecruitmentList)
        }
        holder.bind(responseRecruitmentList)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ResponseRecruitmentList>() {
        override fun areItemsTheSame(
            oldItem: ResponseRecruitmentList,
            newItem: ResponseRecruitmentList
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ResponseRecruitmentList,
            newItem: ResponseRecruitmentList
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class OnClickListener(val clickListener: (responseRecruitmentList: ResponseRecruitmentList) -> Unit) {
        fun onClick(responseRecruitmentList: ResponseRecruitmentList) = clickListener(responseRecruitmentList)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val resultList: MutableList<ResponseRecruitmentList> = ArrayList()

                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    resultList.addAll(responseRecruitmentList)
                } else {
                    for (recruitmentList in responseRecruitmentList) {
                        if (recruitmentList.position!!.contains(charSearch)) {
                            resultList.add(recruitmentList)
                        }
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = resultList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results?.values != null) {
                    submitList(results.values as List<ResponseRecruitmentList>)
                }
            }

        }
    }


}