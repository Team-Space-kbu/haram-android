package com.space.book.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.R
import com.space.book.databinding.ItemSearchBookBinding
import com.space.shared.data.book.Search


internal class SearchItemAdapter(
    private val item: List<Search>,
    private val itemHandler: ItemHandler
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

    interface ItemHandler {
        fun clickSearch(search: Search)
    }
}

internal class SearchItemEmptyViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): SearchItemEmptyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search_empty, parent, false)
            return SearchItemEmptyViewHolder(view)
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
        itemHandler: SearchItemAdapter.ItemHandler
    ) {
        binding.search = search
        binding.searchBackground.setOnClickListener {
            itemHandler.clickSearch(search)
        }
    }

}
