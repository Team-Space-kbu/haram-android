package com.space.book.ui.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.ui.search.adapter.viewholder.SearchItemEmptyViewHolder
import com.space.book.ui.search.adapter.viewholder.SearchItemViewHolder
import com.space.core_ui.ParamsItemHandler
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
            SearchItemEmptyViewHolder.newInstance(parent)
        } else {
            SearchItemViewHolder.newInstance(parent)
        }
    }

    override fun getItemCount() = if (item.isEmpty()) 1 else item.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchItemViewHolder) {
            holder.bindItem(item[position], itemHandler)
        }
    }
}






