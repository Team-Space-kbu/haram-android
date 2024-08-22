package com.space.core_ui.binding.adapter.item.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.util.ParamsItemHandler
import com.space.core_ui.databinding.ItemImageSliderBinding


class ImageSliderAdapter(
    private val slider: List<String>,
    private val visibility: Boolean = true,
    private val itemHandler: ParamsItemHandler<String>
) : RecyclerView.Adapter<ImageSliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder =
        ImageSliderViewHolder.newInstance(parent)

    override fun getItemCount() = if (visibility) 1 else 0

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) =
        holder.bindItem(slider, itemHandler)
}

class ImageSliderViewHolder(
    private val binding: ItemImageSliderBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ImageSliderViewHolder {
            val binding =
                ItemImageSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ImageSliderViewHolder(binding)
        }
    }

    fun bindItem(
        slider: List<String>,
        itemHandler: ParamsItemHandler<String>
    ) {
        binding.viewPager2.adapter = ImageSliderItemAdapter(slider, itemHandler)
    }
}

