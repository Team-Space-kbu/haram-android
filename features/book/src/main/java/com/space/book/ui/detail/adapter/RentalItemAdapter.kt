package com.space.book.ui.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.ui.detail.adapter.holder.ItemEmptyViewHolder
import com.space.book.ui.detail.adapter.holder.ItemRentalViewHolder
import com.space.shared.data.book.Rental


internal class RentalItemAdapter(
    private val item: List<Rental>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (item.isEmpty())
            ItemEmptyViewHolder.newInstance(parent)
        else
            ItemRentalViewHolder.newInstance(parent)

    override fun getItemCount() = if (item.isEmpty()) 1 else item.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemRentalViewHolder) {
            holder.bindItem(item[position])
        }
    }

}



