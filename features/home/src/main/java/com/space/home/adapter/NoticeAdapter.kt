package com.space.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.home.R

internal class NoticeAdapter(

) : RecyclerView.Adapter<NoticeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder =
        NoticeViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {}

    override fun getItemCount(): Int = 1

}

internal class NoticeViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    companion object {
        fun newInstance(parent: ViewGroup): NoticeViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_info_space, parent, false)
            return NoticeViewHolder(view)
        }
    }
}
