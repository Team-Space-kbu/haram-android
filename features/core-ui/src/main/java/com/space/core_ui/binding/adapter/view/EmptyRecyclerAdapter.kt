package com.space.core_ui.binding.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.R
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.databinding.ViewEmptyRecyclerviewBinding
import com.space.core_ui.databinding.ViewHeaderVerticalBinding

class EmptyRecyclerAdapter(
    private val adapter: RecyclerView.Adapter<*>,
) : RecyclerView.Adapter<EmptyRecyclerAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmptyRecyclerAdapterViewHolder =
        EmptyRecyclerAdapterViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: EmptyRecyclerAdapterViewHolder, position: Int) =
        holder.itemBind(adapter)

}

class EmptyRecyclerAdapterViewHolder(
    private val binding: ViewEmptyRecyclerviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): EmptyRecyclerAdapterViewHolder {
            val binding = ViewEmptyRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return EmptyRecyclerAdapterViewHolder(binding)
        }
    }

    fun itemBind(
        adapter: RecyclerView.Adapter<*>,
    ) {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            PaddingItemDecoration(
                itemView.context,
                itemView.context.resources.getDimensionPixelSize(R.dimen.margin_20dp),
                false
            )
        )
    }
}

