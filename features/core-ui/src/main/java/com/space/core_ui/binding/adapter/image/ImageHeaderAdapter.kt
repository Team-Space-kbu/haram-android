package com.space.core_ui.binding.adapter.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.databinding.ItemImgHomeTitleBinding
import com.space.shared.data.core_ui.ImgHomeTitle

class ImageHeaderAdapter(
    private val imgHomeTitle: ImgHomeTitle
) : RecyclerView.Adapter<ImageHeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHeaderViewHolder =
        ImageHeaderViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ImageHeaderViewHolder, position: Int) =
        holder.itemBind(imgHomeTitle)

}

class ImageHeaderViewHolder(
    private val binding: ItemImgHomeTitleBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ImageHeaderViewHolder {
            val binding =
                ItemImgHomeTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ImageHeaderViewHolder(binding)
        }
    }

    fun itemBind(imgHomeTitle: ImgHomeTitle) {
        binding.setVariable(BR.imgTitle, imgHomeTitle)
    }
}

