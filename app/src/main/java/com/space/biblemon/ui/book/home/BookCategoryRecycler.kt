package com.space.biblemon.ui.book.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.shared.data.book.Category
import com.space.biblemon.base.listener.ViewTypeListener
import com.space.biblemon.databinding.ModelBookCategoryLayoutBinding


class BookCategoryRecycler(
    private val viewListener: ViewTypeListener<Int>
) : RecyclerView.Adapter<BookCategoryRecycler.CategoryViewHolder>() {
    private val categories = mutableListOf<Category>()

    fun addItem(searchResultModel: MutableList<Category>) {
        this.categories.addAll(searchResultModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ModelBookCategoryLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bindItem(categories[position])

    inner class CategoryViewHolder(
        private val binding: ModelBookCategoryLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(category: Category) {
            binding.categoryModel = category
            binding.bookCategoryImage.setOnClickListener {
                viewListener.setViewType(category.path)
            }
        }
    }
}

