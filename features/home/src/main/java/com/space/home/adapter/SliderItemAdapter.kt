package com.space.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.databinding.ItemSliderBinding
import com.space.home.BR
import com.space.home.databinding.ItemSliderImgBinding
import com.space.shared.data.home.Slider

internal class SliderItemAdapter(
    private val sliders: List<Slider>,
    private val itemHandler: ItemHandler
) : RecyclerView.Adapter<ItemSliderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSliderViewHolder =
        ItemSliderViewHolder.newInstance(parent, itemHandler)

    override fun getItemCount() = sliders.size

    override fun onBindViewHolder(holder: ItemSliderViewHolder, position: Int) =
        holder.bindItem(sliders[position])

    interface ItemHandler {
        fun clickSlider(slider: Slider)
    }
}

internal class ItemSliderViewHolder(
    private val binding: ItemSliderImgBinding,
    private val itemHandler: SliderItemAdapter.ItemHandler
) : RecyclerView.ViewHolder(binding.root) {


    companion object {
        fun newInstance(
            parent: ViewGroup,
            itemHandler: SliderItemAdapter.ItemHandler
        ): ItemSliderViewHolder {
            val binding = ItemSliderImgBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ItemSliderViewHolder(binding, itemHandler)
        }
    }

    fun bindItem(slider: Slider) {
        binding.setVariable(BR.slider, slider)
    }

}
