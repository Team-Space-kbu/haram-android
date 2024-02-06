package com.space.rothem.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.space.rothem.R

internal class HeaderAdapter: RecyclerView.Adapter<HeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder =
        HeaderViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.title_view).text = "로뎀 스터디룸 예약"
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
                    .inflate(R.layout.item_rothem_text, parent, false)
            return HeaderViewHolder(view)
        }
    }
}

