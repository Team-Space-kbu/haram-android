package com.space.book.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.databinding.ItemBookSliderImgBinding
import com.space.core_ui.BR
import com.space.core_ui.ParamsItemHandler


internal class SliderItemAdapter(
    private val item: List<String>,
    private val itemHandler: ParamsItemHandler<String>
) : RecyclerView.Adapter<ItemSliderImgViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSliderImgViewHolder =
        ItemSliderImgViewHolder.newInstance(parent)

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: ItemSliderImgViewHolder, position: Int) =
        holder.bindItem(item[position], itemHandler)
}

internal class ItemSliderImgViewHolder(
    private val binding: ItemBookSliderImgBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ItemSliderImgViewHolder {
            val binding = ItemBookSliderImgBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ItemSliderImgViewHolder(binding)
        }
    }

    fun bindItem(
        string: String,
        itemHandler: ParamsItemHandler<String>
    ) {
        binding.setVariable(BR.uri, string)
        binding.setVariable(BR.sliderHandler, itemHandler)
    }

}
