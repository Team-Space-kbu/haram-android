package com.space.chapel.ui.databinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.chapel.BR
import com.space.chapel.databinding.ItemChapelInfoBinding
import com.space.shared.data.chapel.ChapelInfo

internal class ChapelInfoAdapter(
    private val chapelInfo: ChapelInfo
) : RecyclerView.Adapter<ChapelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapelViewHolder =
        ChapelViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ChapelViewHolder, position: Int) {
        holder.itemBind(chapelInfo)
    }

}

internal class ChapelViewHolder(
    private val binding: ItemChapelInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ChapelViewHolder {
            val binding =
                ItemChapelInfoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ChapelViewHolder(binding)
        }
    }

    fun itemBind(chapelInfo: ChapelInfo) {
        binding.setVariable(BR.chapel, chapelInfo)
    }
}

