package com.space.book.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.databinding.ShimmerBookDetailBinding


internal class ShimmerDetailAdapter : RecyclerView.Adapter<ShimmerDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerDetailViewHolder =
        ShimmerDetailViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ShimmerDetailViewHolder, position: Int) =
        holder.binding.shimmerViewContainer.startShimmer()

}

internal class ShimmerDetailViewHolder(
    val binding: ShimmerBookDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ShimmerDetailViewHolder {
            val binding = ShimmerBookDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ShimmerDetailViewHolder(binding)
        }
    }
}

