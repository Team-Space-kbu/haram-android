package com.space.chapel.ui.databinding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.chapel.BR
import com.space.chapel.R
import com.space.chapel.databinding.ItemChapelDetailBinding
import com.space.shared.data.chapel.ChapelDetail

internal class ChapelDetailAdapter(
    private val item: List<ChapelDetail>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (item.isEmpty())
            ItemEmptyViewHolder.newInstance(parent)
        else
            ChapelDetailViewHolder.newInstance(parent)


    override fun getItemCount() = if (item.isEmpty()) 1 else item.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ChapelDetailViewHolder) {
            holder.itemBind(item[position])
        }
    }

}

internal class ItemEmptyViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ItemEmptyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_chapel_empty, parent, false)
            return ItemEmptyViewHolder(view)
        }
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

