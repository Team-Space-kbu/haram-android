package com.space.haram_android.ui.book.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.space.data.res.book.data.SearchResultModel
import com.space.haram_android.R
import com.space.haram_android.databinding.ModelBookSearchLayoutBinding
import com.space.haram_android.adapter.BookViewListener
import timber.log.Timber


class BookSearchRecycler(
    private val viewListener: BookViewListener
) : RecyclerView.Adapter<BookSearchRecycler.SearchViewHolder>() {
    private val models = mutableListOf<SearchResultModel>()

    fun addItem(searchResultModel: MutableList<SearchResultModel>) {
        this.models.addAll(searchResultModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.model_book_search_layout,
                parent,
                false
            )
        )

    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) =
        holder.bindItem(models[position])

    inner class SearchViewHolder(
        private val binding: ModelBookSearchLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(searchResultModel: SearchResultModel) {
            binding.searchResult = searchResultModel
            binding.bookSearchBackground.setOnClickListener {
                viewListener.setViewType(searchResultModel.path)
            }
        }
    }

}

