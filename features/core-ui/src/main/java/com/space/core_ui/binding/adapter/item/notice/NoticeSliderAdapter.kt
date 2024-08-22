package com.space.core_ui.binding.adapter.item.notice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.util.ParamsItemHandler
import com.space.core_ui.databinding.ItemImageSliderBinding
import com.space.shared.data.home.Notice


class NoticeSliderAdapter(
    private val slider: List<Notice>,
    private val itemHandler: ParamsItemHandler<Notice>
) : RecyclerView.Adapter<NoticeSliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeSliderViewHolder =
        NoticeSliderViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: NoticeSliderViewHolder, position: Int) =
        holder.bindItem(slider, itemHandler)
}

class NoticeSliderViewHolder(
    private val binding: ItemImageSliderBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): NoticeSliderViewHolder {
            val binding = ItemImageSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return NoticeSliderViewHolder(binding)
        }
    }
    fun bindItem(slider: List<Notice>, itemHandler: ParamsItemHandler<Notice>) {
        binding.viewPager2.adapter = NoticeSliderItemAdapter(slider, itemHandler)
    }
}

