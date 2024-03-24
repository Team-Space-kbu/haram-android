package com.space.book.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.ParamsItemHandler
import com.space.core_ui.databinding.ItemImageSliderBinding

internal class SliderAdapter(
    private val item: ArrayList<String>,
    private val itemHandler: ParamsItemHandler<String>
) : RecyclerView.Adapter<BookSliderViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setList(strings: List<String>) {
        item.addAll(strings)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSliderViewHolder =
        BookSliderViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: BookSliderViewHolder, position: Int) =
        holder.bindItem(item, itemHandler)


}

internal class BookSliderViewHolder(
    private val binding: ItemImageSliderBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): BookSliderViewHolder {
            val binding = ItemImageSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return BookSliderViewHolder(binding)
        }
    }

    fun bindItem(
        item: List<String>,
        itemHandler: ParamsItemHandler<String>
    ) {
        binding.viewPager2.adapter = SliderItemAdapter(item, itemHandler)
    }
}

