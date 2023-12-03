package com.space.book.ui.detail.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.BR
import com.space.book.databinding.ItemBookDetailSignBinding
import com.space.shared.data.book.BookDetailInfo

internal class SignViewHolder(
    private val binding: ItemBookDetailSignBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): SignViewHolder {
            val binding = ItemBookDetailSignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return SignViewHolder(binding)
        }
    }

    fun itemBind(detail: BookDetailInfo) {
        binding.setVariable(BR.detail, detail)
    }
}