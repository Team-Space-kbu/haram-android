package com.space.core_ui.binding.adapter.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.databinding.ItemImgHomeDescriptionBinding
import com.space.shared.data.core_ui.ImgHomeDescription

class ImageDescriptionAdapter(
    private val imgHomeDescription: ImgHomeDescription
) : RecyclerView.Adapter<ImageDescriptionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDescriptionViewHolder =
        ImageDescriptionViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ImageDescriptionViewHolder, position: Int) =
        holder.itemBind(imgHomeDescription)

}

class ImageDescriptionViewHolder(
    private val binding: ItemImgHomeDescriptionBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ImageDescriptionViewHolder {
            val binding =
                ItemImgHomeDescriptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ImageDescriptionViewHolder(binding)
        }
    }

    fun itemBind(imgHomeDescription: ImgHomeDescription) {
        binding.setVariable(BR.imgDescription, imgHomeDescription)
    }
}

