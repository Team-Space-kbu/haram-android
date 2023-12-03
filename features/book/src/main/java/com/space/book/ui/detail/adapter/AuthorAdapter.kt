package com.space.book.ui.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.ui.detail.adapter.holder.AuthorViewHolder
import com.space.shared.data.book.BookDetailInfo

internal class AuthorAdapter(
    val detail: BookDetailInfo
) : RecyclerView.Adapter<AuthorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        return AuthorViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        holder.itemBind(detail)
    }

    override fun getItemCount(): Int = 1
}
