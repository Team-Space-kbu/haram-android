package com.space.rothem.ui.reserved.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.databinding.ItemImgHomeTitleWhiteBinding
import com.space.shared.data.core_ui.ImgHomeTitle

class ReservedLocationAdapter(
    private val imgHomeTitle: ImgHomeTitle
) : RecyclerView.Adapter<ReservedLocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservedLocationViewHolder =
        ReservedLocationViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ReservedLocationViewHolder, position: Int) =
        holder.itemBind(imgHomeTitle)

}

class ReservedLocationViewHolder(
    private val binding: ItemImgHomeTitleWhiteBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ReservedLocationViewHolder {
            val binding =
                ItemImgHomeTitleWhiteBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ReservedLocationViewHolder(binding)
        }
    }

    fun itemBind(imgHomeTitle: ImgHomeTitle) {
        binding.setVariable(BR.imgTitle, imgHomeTitle)
        binding.linear.setPadding(50)
    }
}

