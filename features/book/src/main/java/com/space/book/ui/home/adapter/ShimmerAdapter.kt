package com.space.book.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.databinding.ShimmerBookHomeBinding
import com.space.book.ui.detail.adapter.ShimmerDetailViewHolder


internal class ShimmerAdapter : RecyclerView.Adapter<ShimmerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerViewHolder =
        ShimmerViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ShimmerViewHolder, position: Int) =
        holder.binding.shimmerViewContainer.startShimmer()
}

internal class ShimmerViewHolder(
    val binding: ShimmerBookHomeBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ShimmerViewHolder {
            val binding = ShimmerBookHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
//            val view =
//                LayoutInflater.from(parent.context).inflate(R.layout.shimmer_home, parent, false)
            return ShimmerViewHolder(binding)
        }
    }

    fun bindItem() {
//        binding.shimmerViewContainer.startShimmer()
        binding.shimmerViewContainer.startShimmer()
    }
}

