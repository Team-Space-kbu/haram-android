package com.space.chapel.ui.databinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.chapel.BR
import com.space.chapel.databinding.ItemChapelDetailBinding
import com.space.shared.data.chapel.ChapelDetail

internal class ChapelDetailAdapter(
    private val item: List<ChapelDetail>
) : RecyclerView.Adapter<ChapelDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapelDetailViewHolder =
        ChapelDetailViewHolder.newInstance(parent)

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: ChapelDetailViewHolder, position: Int) {
        holder.itemBind(item[position])
    }

}

internal class ChapelDetailViewHolder(
    private val binding: ItemChapelDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ChapelDetailViewHolder {
            val binding =
                ItemChapelDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ChapelDetailViewHolder(binding)
        }
    }

    fun itemBind(chapelDetail: ChapelDetail) {
        binding.setVariable(BR.detail, chapelDetail)
    }
}

