package com.space.core_ui.binding.adapter.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.databinding.ItemImgHomeDescriptionBoxBinding
import com.space.shared.data.core_ui.ImgHomeDescription

class ImageDescriptionBoxAdapter(
    private val imgHomeDescription: ImgHomeDescription
) : RecyclerView.Adapter<ImageDescriptionBoxViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDescriptionBoxViewHolder =
        ImageDescriptionBoxViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ImageDescriptionBoxViewHolder, position: Int) =
        holder.itemBind(imgHomeDescription)

}

class ImageDescriptionBoxViewHolder(
    private val binding: ItemImgHomeDescriptionBoxBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ImageDescriptionBoxViewHolder {
            val binding =
                ItemImgHomeDescriptionBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ImageDescriptionBoxViewHolder(binding)
        }
    }

    fun itemBind(imgHomeDescription: ImgHomeDescription) {
        binding.setVariable(BR.imgDescription, imgHomeDescription)
    }
}

