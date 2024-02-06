package com.space.rothem.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.ParamsItemHandler
import com.space.rothem.BR
import com.space.rothem.databinding.ItemRothemImgBinding
import com.space.shared.data.rothem.RothemNoticeDetail

internal class NoticeItemAdapter(
    private val sliders: List<RothemNoticeDetail>,
    private val itemHandler: ParamsItemHandler<RothemNoticeDetail>
) : RecyclerView.Adapter<ItemNoticeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNoticeViewHolder =
        ItemNoticeViewHolder.newInstance(parent)

    override fun getItemCount() = sliders.size

    override fun onBindViewHolder(holder: ItemNoticeViewHolder, position: Int) =
        holder.bindItem(sliders[position], itemHandler)

}

internal class ItemNoticeViewHolder(
    private val binding: ItemRothemImgBinding
) : RecyclerView.ViewHolder(binding.root) {


    companion object {
        fun newInstance(
            parent: ViewGroup
        ): ItemNoticeViewHolder {
            val binding = ItemRothemImgBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ItemNoticeViewHolder(binding)
        }
    }

    fun bindItem(notice: RothemNoticeDetail, itemHandler: ParamsItemHandler<RothemNoticeDetail>) {
        binding.setVariable(BR.notice, notice)
    }

}
