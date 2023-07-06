package com.space.haram_android.ui.book.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.data.response.book.CategoryModel
import com.space.haram_android.databinding.ModelBookCategoryLayoutBinding
import com.space.haram_android.ui.book.adapter.BookViewListener


class BookCategoryRecycler(
    private val viewListener: BookViewListener
) : RecyclerView.Adapter<CategoryViewHolder>() {
    var categoryModels: ArrayList<CategoryModel> = ArrayList()

    fun addItem(categoryModel: CategoryModel) {
        categoryModels.add(categoryModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ModelBookCategoryLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            viewListener
        )

    override fun getItemCount() = categoryModels.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bindItem(categoryModels[position])

}

class CategoryViewHolder(
    private val binding: ModelBookCategoryLayoutBinding,
    private val viewListener: BookViewListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(categoryModel: CategoryModel) {
        binding.categoryModel = categoryModel
        binding.bookCategoryImage.setOnClickListener {
            viewListener.setViewType(categoryModel.url)
        }
    }
}