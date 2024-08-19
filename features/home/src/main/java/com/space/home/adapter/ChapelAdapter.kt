package com.space.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.home.BR
import com.space.home.databinding.ItemInfoChapelBinding
import com.space.shared.data.chapel.ChapelInfo


internal class ChapelAdapter(
    private val status: Boolean,
    private val chapelInfo: ChapelInfo? = ChapelInfo("0", "0", "0", "0", "0")
) : RecyclerView.Adapter<ChapelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapelViewHolder =
        ChapelViewHolder.newInstance(parent)

    override fun getItemCount() = if (status) {
        1
    } else {
        0
    }

    override fun onBindViewHolder(holder: ChapelViewHolder, position: Int) =
        holder.bindItem(chapelInfo!!)
}

internal class ChapelViewHolder(
    private val binding: ItemInfoChapelBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ChapelViewHolder {
            val binding =
                ItemInfoChapelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ChapelViewHolder(binding)
        }
    }

    fun bindItem(
        chapelInfo: ChapelInfo
    ) {
        binding.setVariable(BR.infoChapel, chapelInfo)
    }
}

