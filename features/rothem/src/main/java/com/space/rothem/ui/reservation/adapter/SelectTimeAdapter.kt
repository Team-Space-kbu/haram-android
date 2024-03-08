package com.space.rothem.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.space.rothem.BR
import com.space.rothem.databinding.ItemRothemTimeBinding

internal class SelectTimeAdapter(
    private val title: String,
    private val adapter: SelectTimeItemAdapter,
) : RecyclerView.Adapter<SelectTimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTimeViewHolder =
        SelectTimeViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: SelectTimeViewHolder, position: Int) =
        holder.itemBind(title, adapter)

}

internal class SelectTimeViewHolder(
    private val binding: ItemRothemTimeBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): SelectTimeViewHolder {
            val binding =
                ItemRothemTimeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return SelectTimeViewHolder(binding)
        }
    }

    fun itemBind(
        title: String,
        adapter: SelectTimeItemAdapter
    ) {
        binding.setVariable(BR.timeTitle, title)
        binding.recyclerView.layoutManager =
            FlexboxLayoutManager(itemView.context).apply {
                justifyContent = JustifyContent.FLEX_START
            }
        binding.recyclerView.adapter = adapter
    }
}

