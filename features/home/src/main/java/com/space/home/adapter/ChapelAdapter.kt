package com.space.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.home.BR
import com.space.home.databinding.ItemInfoChapelBinding
import com.space.shared.data.chapel.ChapelInfo
import java.time.LocalTime


internal class ChapelAdapter(
    private val status: Boolean
) : RecyclerView.Adapter<ChapelViewHolder>() {

    private lateinit var chapelInfo: ChapelInfo

    @SuppressLint("NotifyDataSetChanged")
    fun setChapel(chapelInfo: ChapelInfo) {
        this.chapelInfo = chapelInfo
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapelViewHolder =
        ChapelViewHolder.newInstance(parent)

    override fun getItemCount() =
        if (status && ::chapelInfo.isInitialized) {
            1
        } else {
            0
        }

    override fun onBindViewHolder(holder: ChapelViewHolder, position: Int) =
        holder.bindItem(chapelInfo)
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

