package com.space.chapel.ui.databinding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.space.chapel.BR
import com.space.chapel.R
import com.space.chapel.databinding.ItemChapelDetailBinding
import com.space.chapel.databinding.ItemChapelInfoDetailBinding
import com.space.shared.data.chapel.ChapelDetail
import com.space.shared.data.chapel.ChapelInfo

internal class ChapelInfoDetailAdapter(
    private val chapelInfo: ChapelInfo
) : RecyclerView.Adapter<ChapelInfoDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapelInfoDetailViewHolder =
        ChapelInfoDetailViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ChapelInfoDetailViewHolder, position: Int) {
        holder.itemBind(chapelInfo)
    }

}

internal class ChapelInfoDetailViewHolder(
    private val binding: ItemChapelInfoDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ChapelInfoDetailViewHolder {
            val binding =
                ItemChapelInfoDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ChapelInfoDetailViewHolder(binding)
        }
    }

    fun itemBind(chapelInfo: ChapelInfo) {
        binding.setVariable(BR.chapel, chapelInfo)
    }
}

