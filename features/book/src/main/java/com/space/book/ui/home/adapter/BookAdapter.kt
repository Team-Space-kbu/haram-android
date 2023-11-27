package com.space.book.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.book.databinding.ItemBookInfoBinding
import com.space.shared.data.Item
import com.space.shared.data.book.Category

internal class ItemBookAdapter(
    private val item: Item<Category, BookItemAdapter.ItemHandler>
) : RecyclerView.Adapter<BookItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        return BookItemViewHolder.newInstance(parent, item)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        holder.itemBind()
    }

    override fun getItemCount(): Int = 1
}

internal class BookItemViewHolder(
    private val binding: ItemBookInfoBinding,
    private val item: Item<Category, BookItemAdapter.ItemHandler>
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
            item: Item<Category, BookItemAdapter.ItemHandler>
        ): BookItemViewHolder {
            val binding = ItemBookInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return BookItemViewHolder(binding, item)
        }
    }

    fun itemBind() {
        binding.title.text = item.title
        binding.recyclerView.adapter = BookItemAdapter(item.list)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
    }
}