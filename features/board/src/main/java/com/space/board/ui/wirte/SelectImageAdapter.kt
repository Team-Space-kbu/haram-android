package com.space.board.ui.wirte

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.board.databinding.ItemImageListBinding

internal class SelectImageAdapter(
    private val adapter: RecyclerView.Adapter<*>
) : RecyclerView.Adapter<SelectImageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectImageViewHolder =
        SelectImageViewHolder.newInstance(parent)


    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: SelectImageViewHolder, position: Int) =
        holder.bindItem(adapter)
}

internal class SelectImageViewHolder(
    private val binding: ItemImageListBinding,
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): SelectImageViewHolder {
            val binding =
                ItemImageListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SelectImageViewHolder(binding)
        }
    }

    fun bindItem(
        adapter: RecyclerView.Adapter<*>
    ) {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            GridSpaceItemDecoration(spanCount = 4, spacing = 10f.fromDpToPx())
        )
        binding.recyclerView.layoutManager = GridLayoutManager(itemView.context, 4)
    }
}



