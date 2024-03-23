package com.space.notice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.notice.databinding.ItemContentDetailBinding
import com.space.shared.data.notice.NoticeDetail

internal class ContentDetailAdapter(
    private val noticeDetail: NoticeDetail
) : RecyclerView.Adapter<ContentDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentDetailViewHolder =
        ContentDetailViewHolder.newInstance(parent)

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ContentDetailViewHolder, position: Int) =
        holder.itemBind(noticeDetail)


}

internal class ContentDetailViewHolder(
    private val binding: ItemContentDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ContentDetailViewHolder {
            val binding =
                ItemContentDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ContentDetailViewHolder(binding)
        }
    }

    fun itemBind(noticeDetail: NoticeDetail) {
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
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">${noticeDetail.content}",
            "text/html",
            "UTF-8"
        )
    }
}

