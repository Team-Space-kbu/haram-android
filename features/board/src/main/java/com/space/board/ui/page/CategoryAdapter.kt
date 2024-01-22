package com.space.board.ui.page

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.ParamsItemHandler
import com.space.core_ui.R
import com.space.core_ui.databinding.ItemCategoryBinding
import com.space.shared.data.Category
import com.space.shared.data.board.BoardPage

internal class CategoryAdapter(
    private val categories: List<BoardPage>,
    private val itemHandler: ParamsItemHandler<BoardPage>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (categories.isEmpty()) {
            EmptyViewHolder.newInstance(parent)
        } else {
            CategoryViewHolder.newInstance(parent)
        }

    override fun getItemCount() = if (categories.isEmpty()) {
        1
    } else {
        categories.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (holder) {
            is CategoryViewHolder -> holder.bindItem(
                categories[position],
                itemHandler
            )

            else -> {
                holder.itemView.findViewById<TextView>(R.id.textview).text = "게시글 내용이 없습니다."
            }
        }
    }
}

internal class CategoryViewHolder(
    private val binding: ItemCategoryBinding,
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): CategoryViewHolder {
            val binding =
                ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoryViewHolder(binding)
        }
    }

    fun bindItem(categories: BoardPage, itemHandler: ParamsItemHandler<BoardPage>) {
        binding.setVariable(BR.category,  BoardPage.toCategory(categories))
        binding.category.setOnClickListener {
            itemHandler.onClick(categories)
        }
        binding.recyclerView.adapter = CategoryTagAdapter("여기에 태그")
        binding.recyclerView.layoutManager =
            LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
    }
}



