package com.space.core_ui.binding.adapter.item.notice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.util.ParamsItemHandler
import com.space.core_ui.databinding.ItemSliderImgBinding
import com.space.core_ui.BR
import com.space.shared.data.home.Notice

internal class NoticeSliderItemAdapter(
    private val sliders: List<Notice>,
    private val itemHandler: ParamsItemHandler<Notice>
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
        slider: Notice,
        itemHandler: ParamsItemHandler<Notice>
    ) {
        binding.setVariable(BR.sliderUri, slider.thumbnailPath)
        binding.imageView.setOnClickListener {
            itemHandler.onClick(slider)
        }
    }

}
