package com.space.board.ui.page.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.util.ParamsItemHandler
import com.space.core_ui.R
import com.space.core_ui.databinding.ItemCategoryBinding
import com.space.shared.data.board.Boards

class CategoryAdapter(
    private val itemHandler: ParamsItemHandler<Boards>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val categories: ArrayList<Boards> = arrayListOf()
    private var boardType: String = ""

    fun setBoard(boardType: String){
        this.boardType = boardType
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearCategories() {
        categories.clear()
        notifyDataSetChanged()
    }

    fun addCategories(boards: List<Boards>) {
        val noticeSize: Int = categories.size
        categories.addAll(boards)
        notifyItemRangeInserted(noticeSize + 1, categories.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (categories.isEmpty()) {
            EmptyViewHolder.newInstance(parent)
        } else {
            CategoryViewHolder.newInstance(parent)
        }

    override fun getItemCount() = if (categories.isEmpty()) 1 else categories.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (holder) {
            is CategoryViewHolder ->
                holder.bindItem(
                    categories[position],
                    boardType,
                    itemHandler
                )

            else -> {
                holder.itemView.findViewById<TextView>(R.id.empty_textview).text = "게시글 내용이 없습니다."
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

    fun bindItem(
        categories: Boards,
        boardType: String,
        itemHandler: ParamsItemHandler<Boards>
    ) {
        binding.setVariable(BR.category, Boards.toCategory(categories))
        binding.category.setOnClickListener {
            itemHandler.onClick(categories)
        }
        binding.recyclerView.adapter = CategoryTagAdapter(boardType)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
    }
}



