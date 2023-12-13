package com.space.bible.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.bible.R

internal class TodayEmptyViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    companion object {
        fun newInstance(parent: ViewGroup): TodayEmptyViewHolder {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_today_empty, parent, false)
            return TodayEmptyViewHolder(view)
        }
    }
}