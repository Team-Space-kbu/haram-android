package com.space.core_ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.R

class HeaderAdapter(
    private val title: String
) : RecyclerView.Adapter<HeaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        return HeaderViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.header_title).text = title
    }

    override fun getItemCount(): Int = 1

}

class HeaderViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    companion object {
        fun newInstance(parent: ViewGroup): HeaderViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
            return HeaderViewHolder(view)
        }
    }
}