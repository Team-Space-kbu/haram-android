package com.space.biblemon.ui.book.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.base.MainThread
import com.space.biblemon.R
import com.space.biblemon.databinding.ModelBookHomeBannerImgBinding
import timber.log.Timber


class BookBannerRecycler(

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val models: MutableList<String> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    @SuppressLint("NotifyDataSetChanged")
    @MainThread
    fun addItem(searchResultModel: MutableList<String>) {
        if (searchResultModel.isNotEmpty()) {
//            val positionStart = models.size
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
        }
        Timber.d("item size $itemCount,Model Size, ${searchResultModel.size}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchBannerViewHolder =
        SearchBannerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.model_book_home_banner_img,
                parent,
                false
            )
        )

    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (holder is SearchBannerViewHolder) {
//            holder.bindItem(models[position])
//        }
    }

    inner class SearchBannerViewHolder(
        private val binding: ModelBookHomeBannerImgBinding
    ) : RecyclerView.ViewHolder(binding.root) {
//        fun bindItem(image: String) {
//
//        }
    }

}

