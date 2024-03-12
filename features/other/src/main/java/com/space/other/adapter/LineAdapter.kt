package com.space.other.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.other.R

internal class LineAdapter : RecyclerView.Adapter<LineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder =
        LineViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {}

}

internal class LineViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): LineViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_line, parent, false)
            return LineViewHolder(view)
        }
    }

}

