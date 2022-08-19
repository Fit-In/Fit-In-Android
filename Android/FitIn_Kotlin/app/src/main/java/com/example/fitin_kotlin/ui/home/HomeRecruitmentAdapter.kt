package com.example.fitin_kotlin.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitin_kotlin.data.model.network.response.ResponseRecruitmentList
import com.example.fitin_kotlin.databinding.ListItemRecruitmentBinding
import kotlin.math.min

class HomeRecruitmentAdapter(private val onClickListener: OnClickListener) : ListAdapter<ResponseRecruitmentList, HomeRecruitmentAdapter.RecruitmentListViewHolder>(DiffCallback) {

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

    override fun getItemCount(): Int {
        val limit = 3
        return min(super.getItemCount(), limit)
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
}