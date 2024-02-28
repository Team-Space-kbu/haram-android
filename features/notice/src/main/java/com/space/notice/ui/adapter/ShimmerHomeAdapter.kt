package com.space.notice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.JustifyContent
import com.space.notice.databinding.ShimmerNoticeHomeBinding


internal class ShimmerHomeAdapter : RecyclerView.Adapter<ShimmerHomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerHomeViewHolder =
        ShimmerHomeViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ShimmerHomeViewHolder, position: Int) =
        holder.itemBind()

}

internal class ShimmerHomeViewHolder(
    val binding: ShimmerNoticeHomeBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ShimmerHomeViewHolder {
            val binding = ShimmerNoticeHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ShimmerHomeViewHolder(binding)
        }
    }

    fun itemBind() {
        binding.shimmerViewContainer.startShimmer()
        binding.flexBox.justifyContent = JustifyContent.FLEX_START
    }
}

