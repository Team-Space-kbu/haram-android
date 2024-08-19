package com.space.board.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.board.BR
import com.space.board.databinding.ItemBoardCategoryBinding
import com.space.core_ui.util.ParamsItemHandler
import com.space.shared.data.board.BoardCategory


internal class CategoryAdapter(
    private val categories: List<BoardCategory>,
    private val itemHandler: ParamsItemHandler<BoardCategory>
) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder.newInstance(parent)

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bindItem(categories[position], itemHandler)
}

internal class CategoryViewHolder(
    private val binding: ItemBoardCategoryBinding,
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): CategoryViewHolder {
            val binding =
                ItemBoardCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoryViewHolder(binding)
        }
    }

    fun bindItem(categories: BoardCategory, itemHandler: ParamsItemHandler<BoardCategory>) {
        binding.setVariable(BR.category, categories)
        binding.setVariable(BR.handler, itemHandler)
    }
}

