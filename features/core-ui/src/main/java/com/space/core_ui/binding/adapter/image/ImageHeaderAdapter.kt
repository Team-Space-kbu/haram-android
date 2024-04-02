package com.space.core_ui.binding.adapter.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.databinding.ItemImgHomeTitleBinding
import com.space.shared.data.core_ui.ImgHomeTitle

class RoomHeaderAdapter(
    private val imgHomeTitle: ImgHomeTitle
) : RecyclerView.Adapter<RoomHeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHeaderViewHolder =
        RoomHeaderViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: RoomHeaderViewHolder, position: Int) =
        holder.itemBind(imgHomeTitle)

}

class RoomHeaderViewHolder(
    private val binding: ItemImgHomeTitleBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): RoomHeaderViewHolder {
            val binding =
                ItemImgHomeTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RoomHeaderViewHolder(binding)
        }
    }

    fun itemBind(imgHomeTitle: ImgHomeTitle) {
        binding.setVariable(BR.imgTitle, imgHomeTitle)
    }
}

