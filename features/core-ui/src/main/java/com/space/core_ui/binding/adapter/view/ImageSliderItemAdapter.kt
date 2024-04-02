package com.space.core_ui.binding.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.ParamsItemHandler
import com.space.core_ui.databinding.ItemSliderImgBinding

internal class ImageSliderItemAdapter(
    private val sliders: List<String>,
    private val itemHandler: ParamsItemHandler<String>
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

    fun bindItem(
        slider: String,
        itemHandler: ParamsItemHandler<String>
    ) {
        binding.setVariable(BR.sliderUri, slider)
        binding.imageView.setOnClickListener {
            itemHandler.onClick(slider)
        }
    }

}
