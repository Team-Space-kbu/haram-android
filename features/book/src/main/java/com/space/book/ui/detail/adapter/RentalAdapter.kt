package com.space.book.ui.detail.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.ui.detail.adapter.holder.RentalViewHolder
import com.space.shared.data.book.Rental

internal class RentalAdapter : RecyclerView.Adapter<RentalViewHolder>() {

    private var rental: List<Rental> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(list: List<Rental>) {
        rental = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RentalViewHolder {
        return RentalViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: RentalViewHolder, position: Int) {
        holder.itemBind(rental)
    }

    override fun getItemCount(): Int = 1
}

