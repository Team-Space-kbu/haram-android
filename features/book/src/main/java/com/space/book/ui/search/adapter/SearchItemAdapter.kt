package com.space.book.ui.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.BR
import com.space.book.databinding.ItemSearchBookBinding
import com.space.core_ui.util.ParamsItemHandler
import com.space.core_ui.view.holder.ItemEmptyViewHolder
import com.space.shared.data.book.Search


internal class SearchItemAdapter(
    private val item: ArrayList<Search>,
    private val itemHandler: ParamsItemHandler<Search>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Search>) {
        val noticeSize: Int = item.size
        item.addAll(list)
        notifyItemRangeInserted(noticeSize + 1, list.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (item.isEmpty()) {
            ItemEmptyViewHolder.newInstance(parent)
        } else {
            SearchItemViewHolder.newInstance(parent)
        }
    }

    override fun getItemCount() = if (item.isEmpty()) 1 else item.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchItemViewHolder -> holder.bindItem(item[position], itemHandler)
            is ItemEmptyViewHolder -> holder.bindItem("검색 결과가 없습니다.")
        }

    }
}

internal class SearchItemViewHolder(
    private val binding: ItemSearchBookBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): SearchItemViewHolder {
            val binding = ItemSearchBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return SearchItemViewHolder(binding)
        }
    }

    fun bindItem(
        search: Search,
        itemHandler: ParamsItemHandler<Search>
    ) {
        binding.setVariable(BR.search, search)
        binding.searchBackground.setOnClickListener {
            itemHandler.onClick(search)
        }
    }
}





