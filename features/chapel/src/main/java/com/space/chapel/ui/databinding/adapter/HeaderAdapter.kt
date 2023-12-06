package com.space.chapel.ui.databinding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.chapel.R

internal class HeaderAdapter : RecyclerView.Adapter<HeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder =
        HeaderViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {}

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
                    .inflate(R.layout.item_chapel_header, parent, false)
            return HeaderViewHolder(view)
        }
    }
}

