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
//            textZoom = 250
            javaScriptEnabled = false
            setSupportMultipleWindows(true)             // 새창 띄우기 허용여부
            javaScriptCanOpenWindowsAutomatically = true // 자바스크립트가 window.open()을 사용할 수 있도록 설정
            loadWithOverviewMode = true                  // html의 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정
            useWideViewPort = true                       // 화면 사이즈 맞추기 허용여부
            domStorageEnabled = true                     // DOM(html 인식) 저장소 허용여부
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

