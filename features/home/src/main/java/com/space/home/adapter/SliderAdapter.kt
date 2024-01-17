package com.space.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.ParamsItemHandler
import com.space.core_ui.databinding.ItemSliderBinding
import com.space.shared.data.home.Slider


internal class SliderAdapter(
    private val slider: List<Slider>,
    private val itemHandler: ParamsItemHandler<Slider>
) : RecyclerView.Adapter<SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder =
        SliderViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) =
        holder.bindItem(slider, itemHandler)
}

internal class SliderViewHolder(
    private val binding: ItemSliderBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): SliderViewHolder {
            val binding = ItemSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SliderViewHolder(binding)
        }
    }
    fun bindItem(slider: List<Slider>, itemHandler: ParamsItemHandler<Slider>) {
        binding.viewPager2.adapter = SliderItemAdapter(slider, itemHandler)
    }
}

