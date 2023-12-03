package com.space.book.ui.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.ui.detail.adapter.holder.SignViewHolder
import com.space.shared.data.book.BookDetailInfo

internal class SignAdapter(
    val detail: BookDetailInfo
) : RecyclerView.Adapter<SignViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignViewHolder {
        return SignViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: SignViewHolder, position: Int) {
        holder.itemBind(detail)
    }

    override fun getItemCount(): Int = 1
}

