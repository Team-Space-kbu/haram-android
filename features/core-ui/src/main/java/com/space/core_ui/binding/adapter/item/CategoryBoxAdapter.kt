package com.space.core_ui.binding.adapter.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.databinding.ItemCategoryBoxBinding

import com.space.core_ui.util.ParamsItemHandler


class CategoryBoxAdapter(
    private val title: List<String>,
    private val itemHandler: ParamsItemHandler<String>
) : RecyclerView.Adapter<CategoryBoxViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryBoxViewHolder =
        CategoryBoxViewHolder.newInstance(parent)

    override fun getItemCount() = title.size

    override fun onBindViewHolder(holder: CategoryBoxViewHolder, position: Int) =
        holder.bindItem(title[position] , itemHandler)
}

class CategoryBoxViewHolder(
    private val binding: ItemCategoryBoxBinding,
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): CategoryBoxViewHolder {
            val binding =
                ItemCategoryBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoryBoxViewHolder(binding)
        }
    }

    fun bindItem(
        title: String,
        itemHandler: ParamsItemHandler<String>
    ) {
        binding.setVariable(BR.title, title)
        binding.setVariable(BR.handler, itemHandler)
    }
}

