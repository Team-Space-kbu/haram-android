package com.space.notice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.databinding.ItemHeaderDetailBinding
import com.space.core_ui.BR
import com.space.shared.data.notice_bible.NoticeDetail

internal class HeaderDetailAdapter(
    private val noticeDetail: NoticeDetail
) : RecyclerView.Adapter<HeaderDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderDetailViewHolder =
        HeaderDetailViewHolder.newInstance(parent)

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: HeaderDetailViewHolder, position: Int) =
        holder.itemBind(noticeDetail)


}

internal class HeaderDetailViewHolder(
    private val binding: ItemHeaderDetailBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): HeaderDetailViewHolder {
            val binding =
                ItemHeaderDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return HeaderDetailViewHolder(binding)
        }
    }

    fun itemBind(noticeDetail: NoticeDetail) {
        binding.setVariable(BR.detail, noticeDetail)
    }
}

