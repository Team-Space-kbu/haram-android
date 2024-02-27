package com.space.notice.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.R

internal class HeaderAdapter(
    val string: String
) : RecyclerView.Adapter<HeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder =
        HeaderViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.itemBind(string)
    }

}

internal class HeaderViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): HeaderViewHolder {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header, parent, false)
            return HeaderViewHolder(view)
        }
    }

    fun itemBind(string: String) {
        val textview = itemView.findViewById<TextView>(R.id.header_title)
        textview.text = string
        textview.textSize = 16F
    }
}

