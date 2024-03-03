package com.space.rothem.ui.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.databinding.ItemImgHomeDescriptionBinding
import com.space.shared.data.core_ui.ImgHomeDescription

internal class RoomDescriptionAdapter(
    private val imgHomeDescription: ImgHomeDescription
) : RecyclerView.Adapter<RoomDescriptionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomDescriptionViewHolder =
        RoomDescriptionViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: RoomDescriptionViewHolder, position: Int) =
        holder.itemBind(imgHomeDescription)

}

internal class RoomDescriptionViewHolder(
    private val binding: ItemImgHomeDescriptionBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): RoomDescriptionViewHolder {
            val binding =
                ItemImgHomeDescriptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RoomDescriptionViewHolder(binding)
        }
    }

    fun itemBind(imgHomeDescription: ImgHomeDescription) {
        binding.setVariable(BR.imgDescription, imgHomeDescription)
    }
}

