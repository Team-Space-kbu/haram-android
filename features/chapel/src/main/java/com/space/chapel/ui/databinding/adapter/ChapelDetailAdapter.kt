package com.space.chapel.ui.databinding.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.chapel.BR
import com.space.chapel.databinding.ItemChapelDetailBinding
import com.space.core_ui.view.holder.ItemEmptyViewHolder
import com.space.shared.data.chapel.ChapelDetail

internal class ChapelDetailAdapter(
    private val item: MutableList<ChapelDetail>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(
        details: List<ChapelDetail>,
    ) {
        item.addAll(details)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (item.isEmpty())
            ItemEmptyViewHolder.newInstance(parent)
        else
            ChapelDetailViewHolder.newInstance(parent)


    override fun getItemCount() = if (item.isEmpty()) 1 else item.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ChapelDetailViewHolder -> holder.itemBind(item[position])
            is ItemEmptyViewHolder -> holder.bindItem("채플 정보가 없습니다.")
        }
    }

}


internal class ChapelDetailViewHolder(
    private val binding: ItemChapelDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ChapelDetailViewHolder {
            val binding =
                ItemChapelDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ChapelDetailViewHolder(binding)
        }
    }

    fun itemBind(chapelDetail: ChapelDetail) {
        binding.setVariable(BR.detail, chapelDetail)
    }
}

