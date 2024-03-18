package com.space.bible.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.databinding.ItemSliderBinding
import com.space.shared.data.bible.NoticeBible

internal class SliderAdapter(
    private val noticeBible: List<NoticeBible>
) : RecyclerView.Adapter<SliderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bindItem(noticeBible)
    }

    override fun getItemCount(): Int = 1

}

internal class SliderViewHolder(
    private val binding: ItemSliderBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): SliderViewHolder {
            val binding = ItemSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return SliderViewHolder(binding)
        }
    }

    fun bindItem(noticeBible: List<NoticeBible>) {
        binding.viewPager2.adapter = SliderItemAdapter(noticeBible)
    }
}