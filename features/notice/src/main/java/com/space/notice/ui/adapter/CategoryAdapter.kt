package com.space.notice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.ParamsItemHandler
import com.space.core_ui.databinding.ItemCategoryBinding
import com.space.shared.data.notice.Notice

internal class CategoryAdapter(
    private val noticeType: List<Notice>,
    private val itemHandler: ParamsItemHandler<Notice>
) : RecyclerView.Adapter<CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder.newInstance(parent)

    override fun getItemCount(): Int = noticeType.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bindItem(noticeType[position], itemHandler)

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

    fun bindItem(notice: Notice, itemHandler: ParamsItemHandler<Notice>) {
        binding.setVariable(BR.category, Notice.toCategory(notice))
        binding.category.setOnClickListener {
            itemHandler.onClick(notice)
        }
        binding.recyclerView.adapter = CategoryTagAdapter(notice.loopnum)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
    }
}



