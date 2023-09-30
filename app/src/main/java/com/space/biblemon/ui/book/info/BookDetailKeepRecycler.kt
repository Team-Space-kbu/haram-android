package com.space.biblemon.ui.book.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.space.data.res.book.data.KeepInfoModel
import com.space.biblemon.R
import com.space.biblemon.databinding.ModelBookDetaillEmptyKeepLayoutBinding
import com.space.biblemon.databinding.ModelBookDetaillKeepLayoutBinding


class BookDetailKeepRecycler : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val models = mutableListOf<KeepInfoModel>()

    fun addItem(searchResultModel: MutableList<KeepInfoModel>) {
        this.models.addAll(searchResultModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (models.isEmpty()) {
            KeepEmptyViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.model_book_detaill_empty_keep_layout,
                    parent,
                    false
                )
            )

        } else {
            KeepViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.model_book_detaill_keep_layout,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is KeepViewHolder) {
            holder.bindItem(models[position])
        }
    }

    override fun getItemCount() = models.size

    inner class KeepViewHolder(
        private val binding: ModelBookDetaillKeepLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(keepInfoModel: KeepInfoModel) {
            binding.bookKeepInfo = keepInfoModel
        }
    }

    inner class KeepEmptyViewHolder(
        binding: ModelBookDetaillEmptyKeepLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root)
}


