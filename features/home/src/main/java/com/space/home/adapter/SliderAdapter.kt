package com.space.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.data.response.home.data.Slider
import com.space.home.databinding.ItemSliderImgBinding


class SliderAdapter(
    private var slider: List<Slider>
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder =
        SliderViewHolder(
            ItemSliderImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = slider.size

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) =
        holder.bindItem(slider[position])

    interface ItemHandler {
        fun clickSlider(slider: Slider)
    }

    inner class SliderViewHolder(
        private val binding: ItemSliderImgBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(slider: Slider) {
            binding.slider = slider
        }
    }
}

