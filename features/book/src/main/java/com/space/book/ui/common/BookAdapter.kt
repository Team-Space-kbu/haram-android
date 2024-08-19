package com.space.book.ui.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.BR
import com.space.book.databinding.ItemBookInfoBinding
import com.space.core_ui.util.ParamsItemHandler
import com.space.shared.data.BookItem
import com.space.shared.data.book.Category

internal class BookAdapter(
    private var bookItem: BookItem<Category>,
    private val itemHandler: ParamsItemHandler<Category>
) : RecyclerView.Adapter<BookItemViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")
    fun setItem(bookItem: BookItem<Category>) {
        this.bookItem = bookItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder =
        BookItemViewHolder.newInstance(parent)


    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        if (bookItem.list.isNotEmpty()) {
            holder.itemBind(bookItem, itemHandler)
        }
    }


    override fun getItemCount(): Int = if (bookItem.list.isNotEmpty()) 1 else 0
}

internal class BookItemViewHolder(
    private val binding: ItemBookInfoBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): BookItemViewHolder {
            val binding = ItemBookInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return BookItemViewHolder(binding)
        }
    }

    fun itemBind(bookItem: BookItem<Category>, itemHandler: ParamsItemHandler<Category>) {
        binding.setVariable(BR.viewTitle, bookItem.title)
        binding.recyclerView.adapter = BookItemAdapter(bookItem, itemHandler)
    }
}