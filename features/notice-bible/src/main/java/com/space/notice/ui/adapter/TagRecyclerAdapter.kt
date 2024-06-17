package com.space.notice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.space.core_ui.ParamsItemHandler
import com.space.notice.databinding.ItemTagRecyclerviewBinding
import com.space.shared.data.notice.NoticeType

internal class TagRecyclerAdapter(
    private val noticeType: List<NoticeType>,
    private val itemHandler: ParamsItemHandler<NoticeType>
) : RecyclerView.Adapter<TagRecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagRecyclerViewHolder =
        TagRecyclerViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: TagRecyclerViewHolder, position: Int) {
        holder.itemBind(noticeType, itemHandler)
    }

}

internal class TagRecyclerViewHolder(
    private val binding: ItemTagRecyclerviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): TagRecyclerViewHolder {
            val binding =
                ItemTagRecyclerviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return TagRecyclerViewHolder(binding)
        }
    }

    fun itemBind(
        noticeType: List<NoticeType>,
        itemHandler: ParamsItemHandler<NoticeType>
    ) {
        binding.recyclerView.adapter = TagAdapter(noticeType, itemHandler)
        binding.recyclerView.layoutManager = FlexboxLayoutManager(itemView.context).apply {
            justifyContent = JustifyContent.FLEX_START
        }
    }
}

