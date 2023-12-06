package com.space.chapel.ui.databinding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.space.chapel.R
import com.space.shared.data.chapel.ChapelInfo

internal class ChapelInfoAdapter(
    private val chapelInfo: ChapelInfo
) : RecyclerView.Adapter<ChapelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapelViewHolder =
        ChapelViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ChapelViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.chapel_days).text = chapelInfo.confirmationDays
    }

}

internal class ChapelViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ChapelViewHolder {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chapel_info, parent, false)
            return ChapelViewHolder(view)
        }
    }
}

