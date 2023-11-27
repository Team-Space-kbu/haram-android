package com.space.biblemon.ui.book.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.space.shared.data.book.Rental
import com.space.biblemon.R
import com.space.biblemon.databinding.ModelBookDetaillKeepLayoutBinding


class BookDetailKeepRecycler : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val models = mutableListOf<Rental>()

    init {
        setHasStableIds(true)
    }

    fun addItem(searchResultModel: MutableList<Rental>) {
        this.models.addAll(searchResultModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        KeepViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.model_book_detaill_keep_layout,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as KeepViewHolder).bindItem(models[position])


    override fun getItemCount() = models.size

    inner class KeepViewHolder(
        private val binding: ModelBookDetaillKeepLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(rental: Rental) {
            binding.bookKeepInfo = rental
        }
    }

}


