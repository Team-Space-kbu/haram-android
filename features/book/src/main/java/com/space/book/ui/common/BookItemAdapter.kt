package com.space.book.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.databinding.ItemCategoryBinding
import com.space.shared.data.Item
import com.space.shared.data.book.Category


internal class BookItemAdapter(
    private val item: Item<Category, ItemHandler>
) : RecyclerView.Adapter<ItemBookViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemBookViewHolder =
        ItemBookViewHolder.newInstance(parent)

    override fun getItemCount() = item.list.size

    override fun onBindViewHolder(holder: ItemBookViewHolder, position: Int) {
        holder.bindItem(item.list[position], item.event!!)
    }

    interface ItemHandler  {
        fun clickCategory(category: Category)
    }
}

internal class ItemBookViewHolder(
    private val binding: ItemCategoryBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ItemBookViewHolder {
            val binding = ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ItemBookViewHolder(binding)
        }
    }

    fun bindItem(
        category: Category,
        itemHandler: BookItemAdapter.ItemHandler
    ) {
        binding.category = category
        binding.image.setOnClickListener {
            itemHandler.clickCategory(category)
        }
    }

}