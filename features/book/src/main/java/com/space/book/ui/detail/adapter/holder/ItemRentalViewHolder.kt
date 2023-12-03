package com.space.book.ui.detail.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.BR
import com.space.book.databinding.ItemDetaillRentalBinding
import com.space.shared.data.book.Rental

internal class ItemRentalViewHolder(
    private val binding: ItemDetaillRentalBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ItemRentalViewHolder {
            val binding = ItemDetaillRentalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ItemRentalViewHolder(binding)
        }
    }

    fun bindItem(rental: Rental) =
        binding.setVariable(BR.rental, rental)

}