package com.space.haram_android.ui.book.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.base.MainThread
import com.space.data.res.book.data.SearchResultModel
import com.space.haram_android.R
import com.space.haram_android.base.ViewTypeListener
import com.space.haram_android.databinding.ModelBookSearchEmptyLayoutBinding
import com.space.haram_android.databinding.ModelBookSearchLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
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
        val positionStart = models.size
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
        if (models.isEmpty()) {
            SearchEmptyViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.model_book_search_empty_layout,
                    parent,
                    false
                )
            )
        } else {
            SearchViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.model_book_search_layout,
                    parent,
                    false
                )
            )

        }


    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchViewHolder) {
            holder.bindItem(models[position])
        }
    }


    inner class SearchViewHolder(
        private val binding: ModelBookSearchLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(searchResultModel: SearchResultModel) {
            binding.searchResult = searchResultModel
            binding.bookSearchBackground.setOnClickListener {
                viewListener.setViewType(searchResultModel.path)
            }
        }
    }

    inner class SearchEmptyViewHolder(
        binding: ModelBookSearchEmptyLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root)

}

