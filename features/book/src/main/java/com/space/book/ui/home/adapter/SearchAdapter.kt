package com.space.book.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.R

internal class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {}

    override fun getItemCount(): Int = 1
}

internal class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun newInstance(parent: ViewGroup): SearchViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_book_search, parent, false)
            return SearchViewHolder(view)
        }
    }
}