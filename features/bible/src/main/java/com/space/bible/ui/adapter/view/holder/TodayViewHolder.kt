package com.space.bible.ui.adapter.view.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.bible.R
import com.space.core_ui.databinding.ItemImageSliderBinding

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

internal class TodayPrayViewHolder(
    private val binding: ItemImageSliderBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): TodayPrayViewHolder {
            val binding = ItemImageSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return TodayPrayViewHolder(binding)
        }
    }

}