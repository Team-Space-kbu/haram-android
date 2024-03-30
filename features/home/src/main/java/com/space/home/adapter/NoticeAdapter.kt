package com.space.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.ParamsItemHandler
import com.space.home.BR
import com.space.shared.data.home.Notice
import com.space.home.R
import com.space.home.databinding.ItemInfoNoticeBinding

internal class NoticeAdapter(
    private val notice: List<Notice>,
    private val paramsItemHandler: ParamsItemHandler<Notice>
) : RecyclerView.Adapter<NoticeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder =
        NoticeViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bindItem(notice[position], paramsItemHandler)
    }

    override fun getItemCount(): Int = 1

}

internal class NoticeViewHolder(
    private val binding: ItemInfoNoticeBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): NoticeViewHolder {
            val binding =
                ItemInfoNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return NoticeViewHolder(binding)
        }
    }

    fun bindItem(
        notice: Notice,
        paramsItemHandler: ParamsItemHandler<Notice>
    ) {
        binding.setVariable(BR.notice, notice)
        binding.setVariable(BR.noticeHandler, paramsItemHandler)
    }
}
