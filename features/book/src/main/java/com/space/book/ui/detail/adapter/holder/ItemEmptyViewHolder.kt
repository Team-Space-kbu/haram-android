package com.space.book.ui.detail.adapter.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.R

internal class ItemEmptyViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ItemEmptyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_book_detail_empty, parent, false)
            return ItemEmptyViewHolder(view)
        }
    }

}
