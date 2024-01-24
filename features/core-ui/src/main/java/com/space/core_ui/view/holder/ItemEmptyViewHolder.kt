package com.space.core_ui.view.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.R

class ItemEmptyViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ItemEmptyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_empty_text, parent, false)
            return ItemEmptyViewHolder(view)
        }
    }

    fun bindItem(string: String) {
        itemView.findViewById<TextView>(R.id.empty_textview).text = string
    }
}