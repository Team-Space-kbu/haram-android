package com.space.board.ui.page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.board.databinding.ShimmerBoardBinding
import com.space.board.databinding.ShimmerBoardPageBinding


internal class ShimmerSearchAdapter : RecyclerView.Adapter<ShimmerBoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerBoardViewHolder =
        ShimmerBoardViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ShimmerBoardViewHolder, position: Int) =
        holder.binding.shimmerViewContainer.startShimmer()
}

internal class ShimmerBoardViewHolder(
    val binding: ShimmerBoardPageBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ShimmerBoardViewHolder {
            val binding = ShimmerBoardPageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ShimmerBoardViewHolder(binding)
        }
    }
}

