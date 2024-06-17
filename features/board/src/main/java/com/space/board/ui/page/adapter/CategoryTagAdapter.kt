package com.space.board.ui.page.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.R

internal class CategoryTagAdapter(
    private val string: String
) : RecyclerView.Adapter<CategoryTagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryTagViewHolder =
        CategoryTagViewHolder.newInstance(parent)

    override fun getItemCount() = if (string.isBlank()) { 0 } else { 1 }

    override fun onBindViewHolder(holder: CategoryTagViewHolder, position: Int) {
        if (string.isNotBlank()) {
            holder.itemView.findViewById<TextView>(R.id.tag_text_view).text = string
        }
    }
}

internal class CategoryTagViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): CategoryTagViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category_tag, parent, false)
            return CategoryTagViewHolder(view)
        }
    }
}



