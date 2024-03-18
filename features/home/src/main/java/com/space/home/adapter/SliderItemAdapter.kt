package com.space.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.ParamsItemHandler
import com.space.core_ui.databinding.ItemSliderBinding
import com.space.home.BR
import com.space.home.databinding.ItemSliderImgBinding
import com.space.shared.data.home.Slider

internal class SliderItemAdapter(
    private val sliders: List<Slider>,
    private val itemHandler: ParamsItemHandler<Slider>
) : RecyclerView.Adapter<ItemSliderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSliderViewHolder =
        ItemSliderViewHolder.newInstance(parent)

    override fun getItemCount() = sliders.size

    override fun onBindViewHolder(holder: ItemSliderViewHolder, position: Int) =
        holder.bindItem(sliders[position], itemHandler)

}

internal class ItemSliderViewHolder(
    private val binding: ItemSliderImgBinding
) : RecyclerView.ViewHolder(binding.root) {


    companion object {
        fun newInstance(
            parent: ViewGroup
        ): ItemSliderViewHolder {
            val binding = ItemSliderImgBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ItemSliderViewHolder(binding)
        }
    }

    fun bindItem(slider: Slider, itemHandler: ParamsItemHandler<Slider>) {
        binding.setVariable(BR.slider, slider)
    }

}
