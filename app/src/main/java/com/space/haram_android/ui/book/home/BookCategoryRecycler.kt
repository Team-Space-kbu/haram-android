package com.space.haram_android.ui.book.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.space.haram_android.R
import com.space.haram_android.common.data.response.book.CategoryModel
import com.space.haram_android.databinding.ModelBookCategoryLayoutBinding
import com.space.haram_android.ui.FunctionActivity
import com.space.haram_android.ui.book.info.BookDetailFragment
import dagger.hilt.android.internal.managers.FragmentComponentManager


class BookCategoryRecycler : RecyclerView.Adapter<CategoryViewHolder>() {
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
            )
        )

    override fun getItemCount() = categoryModels.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bindItem(categoryModels[position])

}

class CategoryViewHolder(
    private val binding: ModelBookCategoryLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(categoryModel: CategoryModel) {
        binding.categoryModel = categoryModel
        binding.bookCategoryImage.setOnClickListener {
            val functionActivity =
                FragmentComponentManager.findActivity(itemView.context) as FunctionActivity
            val result = categoryModel.url
            functionActivity.supportFragmentManager.setFragmentResult(
                "detailKey",
                bundleOf("pathUrl" to result)
            )
            functionActivity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, BookDetailFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
        }
    }
}