package com.space.rothem.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.ParamsItemHandler
import com.space.rothem.databinding.ItemRothemNoticeBinding
import com.space.shared.data.rothem.RothemNoticeDetail

internal class NoticeAdapter(
    private val slider: List<RothemNoticeDetail>,
    private val itemHandler: ParamsItemHandler<RothemNoticeDetail>
) : RecyclerView.Adapter<NoticeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder =
        NoticeViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) =
        holder.bindItem(slider, itemHandler)
}

internal class NoticeViewHolder(
    private val binding: ItemRothemNoticeBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): NoticeViewHolder {
            val binding = ItemRothemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return NoticeViewHolder(binding)
        }
    }

    fun bindItem(
        slider: List<RothemNoticeDetail>,
        itemHandler: ParamsItemHandler<RothemNoticeDetail>
    ) {
        binding.viewPager2.adapter = NoticeItemAdapter(slider, itemHandler)
    }
}

