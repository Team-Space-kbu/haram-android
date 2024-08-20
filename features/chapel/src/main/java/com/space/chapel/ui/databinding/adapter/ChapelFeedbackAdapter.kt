package com.space.chapel.ui.databinding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.chapel.R

internal class ChapelFeedbackAdapter: RecyclerView.Adapter<ChapelInfoDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapelInfoDetailViewHolder =
        ChapelInfoDetailViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ChapelInfoDetailViewHolder, position: Int) {
    }

}

internal class ChapelInfoDetailViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ChapelInfoDetailViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_chapel_feedback, parent, false)
            return ChapelInfoDetailViewHolder(view)
        }
    }

}

