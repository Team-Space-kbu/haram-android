package com.space.book.ui.detail.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.BR
import com.space.book.databinding.ItemBookDetailAuthorBinding
import com.space.shared.data.book.BookDetailInfo


internal class AuthorViewHolder(
    private val binding: ItemBookDetailAuthorBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): AuthorViewHolder {
            val binding = ItemBookDetailAuthorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return AuthorViewHolder(binding)
        }
    }

    fun itemBind(detail: BookDetailInfo) {
        binding.setVariable(BR.detail, detail)
    }
}