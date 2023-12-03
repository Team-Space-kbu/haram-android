package com.space.book.ui.detail.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.BR
import com.space.book.databinding.ItemBookDetailInfoBinding
import com.space.shared.data.book.BookDetailInfo

internal class DetailInfoViewHolder(
    private val binding: ItemBookDetailInfoBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): DetailInfoViewHolder {
            val binding = ItemBookDetailInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return DetailInfoViewHolder(binding)
        }
    }

    fun itemBind(detail: BookDetailInfo) {
        binding.setVariable(BR.detail, detail)
    }
}