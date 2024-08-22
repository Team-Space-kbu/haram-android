package com.space.rothem.ui.reserved.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.space.rothem.BR
import com.space.rothem.databinding.ItemCheckinTitleBinding

class ReservedHeaderAdapter(
    private val title: String
) : RecyclerView.Adapter<ReservedHeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservedHeaderViewHolder =
        ReservedHeaderViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ReservedHeaderViewHolder, position: Int) =
        holder.itemBind(title)

}

class ReservedHeaderViewHolder(
    private val binding: ItemCheckinTitleBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ReservedHeaderViewHolder {
            val binding =
                ItemCheckinTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ReservedHeaderViewHolder(binding)
        }
    }

    fun itemBind(title: String) {
        binding.setVariable(BR.date, title)
        binding.frame.setPadding(50, 30, 50, 0)
    }
}

