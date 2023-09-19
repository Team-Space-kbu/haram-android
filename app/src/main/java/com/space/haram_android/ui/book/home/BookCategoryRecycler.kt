package com.space.haram_android.ui.book.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.data.res.book.data.CategoryModel
import com.space.data.res.book.data.SearchResultModel
import com.space.haram_android.databinding.ModelBookCategoryLayoutBinding
import com.space.haram_android.adapter.BookViewListener


class BookCategoryRecycler(
    private val viewListener: BookViewListener
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

