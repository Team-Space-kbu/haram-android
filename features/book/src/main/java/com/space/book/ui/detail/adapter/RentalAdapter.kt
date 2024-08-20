package com.space.book.ui.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.ui.detail.adapter.holder.RentalViewHolder
import com.space.shared.data.book.Rental

internal class RentalAdapter(
    private val rental: List<Rental>
) : RecyclerView.Adapter<RentalViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RentalViewHolder {
        return RentalViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(
        holder: RentalViewHolder,
        position: Int
    ) {
        holder.itemBind(rental)
    }

    override fun getItemCount(): Int = 1
}

