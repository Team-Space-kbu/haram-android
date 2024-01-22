package com.space.board.ui.page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.R

internal class EmptyViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): EmptyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_empty_text, parent, false)
            return EmptyViewHolder(view)
        }
    }

}

