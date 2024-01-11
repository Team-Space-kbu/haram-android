package com.space.bible.ui.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.space.bible.R
import com.space.bible.ui.adapter.view.holder.TodayEmptyViewHolder

internal class TodayPrayAdapter(
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TodayEmptyViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.today_title).text = "오늘의 기도 정보가 없습니다."
    }

    override fun getItemCount(): Int = 1

}
