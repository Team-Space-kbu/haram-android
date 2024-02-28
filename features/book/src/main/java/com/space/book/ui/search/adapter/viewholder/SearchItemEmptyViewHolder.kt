package com.space.book.ui.search.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.R

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
