package com.space.book.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.BR
import com.space.book.databinding.ItemBookCategoryBinding
import com.space.core_ui.util.ParamsItemHandler
import com.space.shared.data.book.Category


internal class BookItemAdapter(
    private val bookItem: List<Category>,
    private val itemHandler: ParamsItemHandler<Category>
) : RecyclerView.Adapter<ItemBookViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemBookViewHolder =
        ItemBookViewHolder.newInstance(parent)

    override fun getItemCount() = bookItem.size

    override fun onBindViewHolder(holder: ItemBookViewHolder, position: Int) {
        holder.bindItem(bookItem[position], itemHandler)
    }

}

internal class ItemBookViewHolder(
    private val binding: ItemBookCategoryBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ItemBookViewHolder {
            val binding = ItemBookCategoryBinding.inflate(
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
