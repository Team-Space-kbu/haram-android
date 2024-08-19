package com.space.notice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.util.ParamsItemHandler
import com.space.notice.BR
import com.space.notice.databinding.ItemTagNoticeBinding
import com.space.shared.data.notice.NoticeType

internal class TagAdapter(
    private val noticeType: List<NoticeType>,
    private val itemHandler: ParamsItemHandler<NoticeType>
) : RecyclerView.Adapter<TagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder =
        TagViewHolder.newInstance(parent)

    override fun getItemCount(): Int = noticeType.size

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) =
        holder.itemBind(noticeType[position], itemHandler)


}

internal class TagViewHolder(
    private val binding: ItemTagNoticeBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): TagViewHolder {
            val binding =
                ItemTagNoticeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return TagViewHolder(binding)
        }
    }

    fun itemBind(
        noticeType: NoticeType,
        itemHandler: ParamsItemHandler<NoticeType>
    ) {
        binding.setVariable(BR.noticeType, noticeType)
        binding.setVariable(BR.noticeHandler, itemHandler)
        binding.executePendingBindings()
    }
}

