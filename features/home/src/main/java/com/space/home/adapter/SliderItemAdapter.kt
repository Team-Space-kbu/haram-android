package com.space.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import data.home.Slider
import com.space.home.BR
import com.space.home.R
import com.space.home.databinding.ItemSliderImgBinding

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
    view : View,
    private val itemHandler: SliderItemAdapter.ItemHandler
) : RecyclerView.ViewHolder(view) {

    private val binding: ItemSliderImgBinding

    init {
        binding = DataBindingUtil.bind(view)!!
    }

    companion object {
        fun newInstance(
            parent: ViewGroup,
            itemHandler: SliderItemAdapter.ItemHandler
        ): ItemSliderViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_slider_img, parent, false)
            return ItemSliderViewHolder(view, itemHandler)
        }
    }

    fun bindItem(slider: Slider) {
        binding.setVariable(BR.slider, slider)
    }

}
