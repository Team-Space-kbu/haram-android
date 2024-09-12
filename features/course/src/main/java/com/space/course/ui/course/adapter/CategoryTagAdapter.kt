package com.space.course.ui.course.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.R

internal class CategoryTagAdapter(
    private val string: List<String>
) : RecyclerView.Adapter<CategoryTagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryTagViewHolder =
        CategoryTagViewHolder.newInstance(parent)

    override fun getItemCount(): Int = string.size

    override fun onBindViewHolder(holder: CategoryTagViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tag_text_view).text = string[position]
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



