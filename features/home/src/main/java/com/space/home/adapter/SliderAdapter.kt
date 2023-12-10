package com.space.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.databinding.ItemSliderBinding
import com.space.shared.data.home.Slider


internal class SliderAdapter(
    private val slider: List<Slider>,
    private val itemHandler: SliderItemAdapter.ItemHandler
) : RecyclerView.Adapter<SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder =
        SliderViewHolder.newInstance(parent, slider, itemHandler)


    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) =
        holder.bindItem()



}

internal class SliderViewHolder(
    private val binding: ItemSliderBinding,
    private val slider: List<Slider>,
    private val itemHandler: SliderItemAdapter.ItemHandler
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
            slider: List<Slider>,
            itemHandler: SliderItemAdapter.ItemHandler
        ): SliderViewHolder {
            val binding = ItemSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SliderViewHolder(binding, slider, itemHandler)
        }
    }
    fun bindItem() {
        binding.viewPager2.adapter = SliderItemAdapter(slider, itemHandler)
    }
}

