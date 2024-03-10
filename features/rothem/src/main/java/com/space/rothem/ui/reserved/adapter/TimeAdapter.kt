package com.space.rothem.ui.reserved.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.rothem.BR
import com.space.rothem.databinding.ItemRothemInfoBinding

internal class TimeAdapter(
    val adapter: ConcatAdapter
) : RecyclerView.Adapter<TimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder =
        TimeViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) =
        holder.itemBind(adapter)

}

internal class TimeViewHolder(
    private val binding: ItemRothemInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): TimeViewHolder {
            val binding =
                ItemRothemInfoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return TimeViewHolder(binding)
        }
    }

    fun itemBind(
        adapter: ConcatAdapter
    ) {
        binding.setVariable(BR.imgTitle, "시간선택")
        binding.recyclerView.layoutManager =
            LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }
}

