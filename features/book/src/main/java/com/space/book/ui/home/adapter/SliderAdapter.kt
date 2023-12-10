package com.space.book.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.databinding.ItemSliderBinding
import com.space.shared.data.Item

internal class SliderAdapter(
    private val item: Item<String, SliderItemAdapter.ItemHandler>
) : RecyclerView.Adapter<BookSliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSliderViewHolder =
        BookSliderViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: BookSliderViewHolder, position: Int) =
        holder.bindItem(item.list)


}

internal class BookSliderViewHolder(
    private val binding: ItemSliderBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): BookSliderViewHolder {
            val binding = ItemSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return BookSliderViewHolder(binding)
        }
    }

    fun bindItem(item: List<String>) {
        binding.viewPager2.adapter = SliderItemAdapter(item)
    }
}

