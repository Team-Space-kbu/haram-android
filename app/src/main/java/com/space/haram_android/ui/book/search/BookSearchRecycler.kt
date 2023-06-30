package com.space.haram_android.ui.book.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.space.haram_android.R
import com.space.haram_android.common.data.response.book.data.SearchResultModel
import com.space.haram_android.databinding.ModelBookSearchLayoutBinding
import com.space.haram_android.ui.FunctionActivity
import com.space.haram_android.ui.book.info.BookDetailFragment
import dagger.hilt.android.internal.managers.FragmentComponentManager


class BookSearchRecycler : RecyclerView.Adapter<SearchViewHolder>() {
    var models: ArrayList<SearchResultModel> = ArrayList()

    fun addItem(searchResultModel: SearchResultModel) {
        models.add(searchResultModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(
            ModelBookSearchLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) =
        holder.bindItem(models[position])

}

class SearchViewHolder(
    private val binding: ModelBookSearchLayoutBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(searchResultModel: SearchResultModel) {
        binding.searchResult = searchResultModel
        binding.bookSearchBackground.setOnClickListener {
            val functionActivity =
                FragmentComponentManager.findActivity(itemView.context) as FunctionActivity
            val result = searchResultModel.info
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