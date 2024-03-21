package com.space.board.ui.wirte

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.board.BR
import com.space.board.databinding.ItemImageSelectBinding

internal class ImageAdapter(
    private val image: ArrayList<String>
) : RecyclerView.Adapter<ImageViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(image: String) {
        this.image.add(image)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder.newInstance(parent)


    override fun getItemCount() = image.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) =
        holder.bindItem(image[position])
}

internal class ImageViewHolder(
    private val binding: ItemImageSelectBinding,
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ImageViewHolder {
            val binding =
                ItemImageSelectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ImageViewHolder(binding)
        }
    }

    fun bindItem(
        image: String
    ) {
        binding.setVariable(BR.imageUrl, image)
    }
}



