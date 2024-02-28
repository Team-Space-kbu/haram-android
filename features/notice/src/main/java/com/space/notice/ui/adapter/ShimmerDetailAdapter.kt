package com.space.notice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.notice.databinding.ShimmerNoticeDetailBinding
import com.space.notice.databinding.ShimmerNoticeSearchBinding


internal class ShimmerDetailAdapter : RecyclerView.Adapter<ShimmerDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerDetailViewHolder =
        ShimmerDetailViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ShimmerDetailViewHolder, position: Int) =
        holder.binding.shimmerViewContainer.startShimmer()
}

internal class ShimmerDetailViewHolder(
    val binding: ShimmerNoticeDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ShimmerDetailViewHolder {
            val binding = ShimmerNoticeDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ShimmerDetailViewHolder(binding)
        }
    }
}

