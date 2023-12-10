package com.space.book.ui.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.databinding.ItemBookInfoBinding
import com.space.shared.data.Item
import com.space.shared.data.book.Category

internal class BookAdapter(
    private var item: Item<Category, BookItemAdapter.ItemHandler>
) : RecyclerView.Adapter<BookItemViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")
    fun setItem(item: Item<Category, BookItemAdapter.ItemHandler>) {
        this.item = item
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder =
        BookItemViewHolder.newInstance(parent, item)


    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        if (item.list.isNotEmpty()) {
            holder.itemBind()
        }
    }


    override fun getItemCount(): Int = if (item.list.isNotEmpty()) 1 else 0
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
        binding.recyclerView.adapter = BookItemAdapter(item)
    }
}