package com.space.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.ParamsItemHandler
import com.space.core_ui.databinding.ItemImageSliderBinding
import com.space.shared.data.home.Notice


internal class SliderAdapter(
    private val slider: List<Notice>,
    private val itemHandler: ParamsItemHandler<Notice>
) : RecyclerView.Adapter<SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder =
        SliderViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) =
        holder.bindItem(slider, itemHandler)
}

internal class SliderViewHolder(
    private val binding: ItemImageSliderBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): SliderViewHolder {
            val binding = ItemImageSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SliderViewHolder(binding)
        }
    }
    fun bindItem(slider: List<Notice>, itemHandler: ParamsItemHandler<Notice>) {
        binding.viewPager2.adapter = SliderItemAdapter(slider, itemHandler)
    }
}

