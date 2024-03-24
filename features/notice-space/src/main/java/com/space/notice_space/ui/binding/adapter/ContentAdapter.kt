package com.space.notice_space.ui.binding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.notice_space.BR
import com.space.notice_space.databinding.ItemNoticeContentBinding


internal class ShimmerAdapter(
    private val content: String
) : RecyclerView.Adapter<ContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder =
        ContentViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.binding.setVariable(BR.noticeContent, content)
    }
}

internal class ContentViewHolder(
    val binding: ItemNoticeContentBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ContentViewHolder {
            val binding = ItemNoticeContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ContentViewHolder(binding)
        }
    }
}

