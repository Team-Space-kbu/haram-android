package com.space.biblemon.ui.book.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.base.MainThread
import com.space.biblemon.BR
import response.book.data.SearchResultModel
import com.space.biblemon.R
import com.space.biblemon.base.listener.ViewTypeListener
import com.space.biblemon.databinding.ModelBookSearchLayoutBinding
import timber.log.Timber


class BookSearchRecycler(
    private val viewListener: ViewTypeListener<Int>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val models: MutableList<SearchResultModel> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    @SuppressLint("NotifyDataSetChanged")
    @MainThread
    fun addItem(searchResultModel: MutableList<SearchResultModel>) {
//        val positionStart = models.size
        this.models.addAll(searchResultModel)
        notifyDataSetChanged()
//        if (models.size - searchResultModel.size == 0) {
//            notifyDataSetChanged()
//        } else {
//            notifyItemRangeInserted(
//                positionStart + 1,
//                searchResultModel.size
//            )
//        }
        Timber.d("item size $itemCount,Model Size, ${searchResultModel.size}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        SearchViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.model_book_search_layout,
                parent,
                false
            )
        )

    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchViewHolder).bindItem(models[position])
    }



    inner class SearchViewHolder(
        private val binding: ModelBookSearchLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(searchResultModel: SearchResultModel) {
            binding.setVariable(BR.searchResult, searchResultModel)
            binding.bookSearchBackground.setOnClickListener {
                viewListener.setViewType(searchResultModel.path)
            }
        }
    }

}

