package com.space.haram_android.ui.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.space.haram_android.R
import com.space.haram_android.common.data.response.book.CategoryModel
import com.space.haram_android.common.data.response.home.data.NewsModel
import com.space.haram_android.databinding.ModelBookCategoryLayoutBinding
import com.space.haram_android.databinding.ModelHomeNewsImgBinding
import com.space.haram_android.ui.FunctionActivity
import dagger.hilt.android.internal.managers.FragmentComponentManager


class BookCategoryRecycler : RecyclerView.Adapter<CategoryViewHolder>() {
    private val newsModels: ArrayList<CategoryModel> = ArrayList()

    fun addItem(categoryModel: CategoryModel) {
        newsModels.add(categoryModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ModelBookCategoryLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = newsModels.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bindItem(newsModels[position])

}

class CategoryViewHolder(
    private val binding: ModelBookCategoryLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(categoryModel: CategoryModel) {
        Glide.with(itemView.context).load(categoryModel.image).centerCrop()
            .into(binding.bookCategoryImage)
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