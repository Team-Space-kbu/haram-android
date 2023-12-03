package com.space.book.ui.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.ui.detail.adapter.holder.DetailInfoViewHolder
import com.space.shared.data.book.BookDetailInfo

internal class DetailInfoAdapter(
    val detail: BookDetailInfo
) : RecyclerView.Adapter<DetailInfoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailInfoViewHolder {
        return DetailInfoViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: DetailInfoViewHolder, position: Int) {
        holder.itemBind(detail)
    }

    override fun getItemCount(): Int = 1
}

