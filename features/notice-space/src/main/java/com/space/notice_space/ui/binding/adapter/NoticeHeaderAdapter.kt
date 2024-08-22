package com.space.notice_space.ui.binding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.databinding.ItemHeaderDetailBinding
import com.space.core_ui.BR
import com.space.shared.data.notice_space.NoticeSpace

internal class NoticeHeaderAdapter(
    private val noticeSpace: NoticeSpace
) : RecyclerView.Adapter<NoticeHeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeHeaderViewHolder =
        NoticeHeaderViewHolder.newInstance(parent)

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: NoticeHeaderViewHolder, position: Int) =
        holder.itemBind(noticeSpace)


}

internal class NoticeHeaderViewHolder(
    private val binding: ItemHeaderDetailBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): NoticeHeaderViewHolder {
            val binding =
                ItemHeaderDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return NoticeHeaderViewHolder(binding)
        }
    }

    fun itemBind(detail: NoticeSpace) {
        binding.setVariable(BR.title, detail.title)
        binding.setVariable(BR.date, detail.createdAt)
        binding.setVariable(BR.name, detail.createdBy)
    }
}

