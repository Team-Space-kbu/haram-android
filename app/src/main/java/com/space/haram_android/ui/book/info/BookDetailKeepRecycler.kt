package com.space.haram_android.ui.book.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.data.response.book.data.BookKeepInfo
import com.space.haram_android.databinding.ModelBookDetaillKeepLaypoutBinding


class BookDetailKeepRecycler : RecyclerView.Adapter<KeepViewHolder>() {
    var models: ArrayList<BookKeepInfo> = ArrayList()

    fun addItem(bookKeepInfo: BookKeepInfo) {
        models.add(bookKeepInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeepViewHolder =
        KeepViewHolder(
            ModelBookDetaillKeepLaypoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: KeepViewHolder, position: Int) =
        holder.bindItem(models[position])

}

class KeepViewHolder(
    private val binding: ModelBookDetaillKeepLaypoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(bookKeepInfo: BookKeepInfo) {
        binding.bookKeepInfo = bookKeepInfo
    }
}