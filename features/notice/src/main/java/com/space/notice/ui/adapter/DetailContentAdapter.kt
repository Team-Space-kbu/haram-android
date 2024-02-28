package com.space.notice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.lifecycle.findViewTreeLifecycleOwner

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
            javaScriptEnabled = false
            loadWithOverviewMode = true
            useWideViewPort = true
            textZoom = 250
        }
        binding.content.isHorizontalScrollBarEnabled = false
        binding.content.isVerticalScrollBarEnabled = false
        binding.content.isScrollbarFadingEnabled = false
        binding.content.loadData(
            "<style>img{display: inline; height: auto; max-width: 100%;}</style>" + noticeDetail.content,
            "text/html",
            "UTF-8"
        )
    }
}

