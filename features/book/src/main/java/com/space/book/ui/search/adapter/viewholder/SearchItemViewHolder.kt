package com.space.book.ui.search.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.BR
import com.space.book.databinding.ItemSearchBookBinding
import com.space.core_ui.util.ParamsItemHandler
import com.space.shared.data.book.Search

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