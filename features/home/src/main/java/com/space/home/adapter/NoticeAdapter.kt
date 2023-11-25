package com.space.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.space.data.data.home.Notice
import com.space.home.R

internal class NoticeAdapter(
    private val notice: List<Notice>
) : RecyclerView.Adapter<NoticeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder =
        NoticeViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bindItem(notice[position])
    }

    override fun getItemCount(): Int = 1

}

internal class NoticeViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    companion object {
        fun newInstance(parent: ViewGroup): NoticeViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_info_notice, parent, false)
            return NoticeViewHolder(view)
        }
    }

    fun bindItem(notice: Notice){
        itemView.findViewById<TextView>(R.id.home_notice_text).text = notice.title
    }
}
