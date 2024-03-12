package com.space.rothem.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.NonParamsItemHandler
import com.space.rothem.databinding.ItemReservedInfoBinding

internal class ReservedAdapter(
    private val status: Int,
    private val nonParamsItemHandler: NonParamsItemHandler
) : RecyclerView.Adapter<ReservedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservedViewHolder =
        ReservedViewHolder.newInstance(parent)

    override fun getItemCount() = if (status > 0) 1 else 0

    override fun onBindViewHolder(holder: ReservedViewHolder, position: Int) =
        holder.itemBind(nonParamsItemHandler)

}

internal class ReservedViewHolder(
    private val binding: ItemReservedInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ReservedViewHolder {
            val binding = ItemReservedInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ReservedViewHolder(binding)
        }
    }

    fun itemBind(
        nonParamsItemHandler: NonParamsItemHandler
    ) {
        binding.reserved.setOnClickListener {
            nonParamsItemHandler.onClick()
        }
    }
}

