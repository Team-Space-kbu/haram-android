package com.space.partners.ui.home.databinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.partners.databinding.ShimmerPartnerHomeBinding


internal class ShimmerHomeAdapter : RecyclerView.Adapter<ShimmerHomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerHomeViewHolder =
        ShimmerHomeViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ShimmerHomeViewHolder, position: Int) =
        holder.binding.shimmerViewContainer.startShimmer()
}

internal class ShimmerHomeViewHolder(
    val binding: ShimmerPartnerHomeBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ShimmerHomeViewHolder {
            val binding = ShimmerPartnerHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ShimmerHomeViewHolder(binding)
        }
    }
}

