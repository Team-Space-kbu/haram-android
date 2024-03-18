package com.space.board.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.board.R
import com.space.shared.data.board.BoardDetail

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
                LayoutInflater.from(parent.context).inflate(R.layout.item_gray_line, parent, false)
            return LineViewHolder(view)
        }
    }

}



