package com.space.book.ui.search.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.space.book.R

internal class SearchViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): SearchViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search_info, parent, false)
            return SearchViewHolder(view)
        }
    }

    fun bindItem() {
        itemView.findViewById<TextView>(R.id.search_title).text = "검색 결과"
    }
}