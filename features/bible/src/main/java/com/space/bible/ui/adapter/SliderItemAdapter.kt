package com.space.bible.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.bible.BR
import com.space.bible.databinding.ItemBibleSliderImgBinding
import com.space.shared.data.bible.NoticeBible


internal class SliderItemAdapter(
    private val item: List<NoticeBible>
) : RecyclerView.Adapter<ItemSliderImgViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSliderImgViewHolder =
        ItemSliderImgViewHolder.newInstance(parent)

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: ItemSliderImgViewHolder, position: Int) =
        holder.bindItem(item[position])


    interface ItemHandler {
        fun clickSlider(string: String)
    }
}

internal class ItemSliderImgViewHolder(
    private val binding: ItemBibleSliderImgBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ItemSliderImgViewHolder {
            val binding = ItemBibleSliderImgBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ItemSliderImgViewHolder(binding)
        }
    }

    fun bindItem(noticeBible: NoticeBible) {
        binding.setVariable(BR.notice, noticeBible)
    }

}
