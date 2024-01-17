package com.space.book.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.BR
import com.space.book.databinding.ItemCategoryBinding
import com.space.core_ui.ParamsItemHandler
import com.space.shared.data.BookItem
import com.space.shared.data.book.Category


internal class BookItemAdapter(
    private val bookItem: BookItem<Category>,
    private val itemHandler: ParamsItemHandler<Category>
) : RecyclerView.Adapter<ItemBookViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemBookViewHolder =
        ItemBookViewHolder.newInstance(parent)

    override fun getItemCount() = bookItem.list.size

    override fun onBindViewHolder(holder: ItemBookViewHolder, position: Int) {
        holder.bindItem(bookItem.list[position], itemHandler)
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
        itemHandler: ParamsItemHandler<Category>
    ) {
        binding.setVariable(BR.category, category)
        binding.setVariable(BR.handler, itemHandler)
    }

}
