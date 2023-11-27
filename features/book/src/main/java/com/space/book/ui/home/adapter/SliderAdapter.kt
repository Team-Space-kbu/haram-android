package com.space.book.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.book.databinding.ItemBookSliderBinding
import com.space.shared.data.Item

internal class SliderAdapter(
    private val item: Item<String, SliderItemAdapter.ItemHandler>
) : RecyclerView.Adapter<BookSliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSliderViewHolder =
        BookSliderViewHolder.newInstance(parent, item)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: BookSliderViewHolder, position: Int) = holder.bindItem()


}

internal class BookSliderViewHolder(
    private val binding: ItemBookSliderBinding,
    private val item: Item<String, SliderItemAdapter.ItemHandler>
) : RecyclerView.ViewHolder(binding.root) {


    companion object {
        fun newInstance(
            parent: ViewGroup,
            item: Item<String, SliderItemAdapter.ItemHandler>
        ): BookSliderViewHolder {
            val binding = ItemBookSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return BookSliderViewHolder(binding, item)
        }
    }

    fun bindItem() {
        binding.viewPager2.adapter = SliderItemAdapter(item)
    }
}

