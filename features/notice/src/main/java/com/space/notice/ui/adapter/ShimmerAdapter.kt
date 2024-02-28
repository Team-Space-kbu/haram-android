package com.space.notice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.notice.databinding.ShimmerNoticeSearchBinding


internal class ShimmerAdapter : RecyclerView.Adapter<ShimmerSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerSearchViewHolder =
        ShimmerSearchViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ShimmerSearchViewHolder, position: Int) =
        holder.binding.shimmerViewContainer.startShimmer()
}

internal class ShimmerSearchViewHolder(
    val binding: ShimmerNoticeSearchBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ShimmerSearchViewHolder {
            val binding = ShimmerNoticeSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ShimmerSearchViewHolder(binding)
        }
    }
}

