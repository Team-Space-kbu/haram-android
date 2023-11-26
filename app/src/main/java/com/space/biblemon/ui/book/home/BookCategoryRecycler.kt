package com.space.biblemon.ui.book.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import response.book.data.CategoryModel
import com.space.biblemon.base.listener.ViewTypeListener
import com.space.biblemon.databinding.ModelBookCategoryLayoutBinding


class BookCategoryRecycler(
    private val viewListener: ViewTypeListener<Int>
) : RecyclerView.Adapter<BookCategoryRecycler.CategoryViewHolder>() {
    private val categoryModels = mutableListOf<CategoryModel>()

    fun addItem(searchResultModel: MutableList<CategoryModel>) {
        this.categoryModels.addAll(searchResultModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ModelBookCategoryLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = categoryModels.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bindItem(categoryModels[position])

    inner class CategoryViewHolder(
        private val binding: ModelBookCategoryLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(categoryModel: CategoryModel) {
            binding.categoryModel = categoryModel
            binding.bookCategoryImage.setOnClickListener {
                viewListener.setViewType(categoryModel.path)
            }
        }
    }
}

