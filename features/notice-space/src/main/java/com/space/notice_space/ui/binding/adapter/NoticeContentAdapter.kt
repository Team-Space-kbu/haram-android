package com.space.notice_space.ui.binding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.databinding.ItemContentDetailBinding
import com.space.shared.data.notice_space.NoticeSpace

internal class NoticeContentAdapter(
    private val noticeSpace: NoticeSpace
) : RecyclerView.Adapter<NoticeContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeContentViewHolder =
        NoticeContentViewHolder.newInstance(parent)

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: NoticeContentViewHolder, position: Int) =
        holder.itemBind(noticeSpace)


}

internal class NoticeContentViewHolder(
    private val binding: ItemContentDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): NoticeContentViewHolder {
            val binding =
                ItemContentDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return NoticeContentViewHolder(binding)
        }
    }

    fun itemBind(noticeSpace: NoticeSpace) {
        binding.content.settings.apply {
            loadWithOverviewMode = true
            useWideViewPort = true
//            textZoom = 250
            javaScriptEnabled = false
        }
        binding.content.isHorizontalScrollBarEnabled = false
        binding.content.isVerticalScrollBarEnabled = false
        binding.content.isScrollbarFadingEnabled = false
        binding.content.loadData(
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">${noticeSpace.content}",
            "text/html",
            "UTF-8"
        )
    }
}

