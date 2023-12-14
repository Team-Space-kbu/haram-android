package com.space.bible.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.bible.BR
import com.space.bible.databinding.ItemBibleHeaderBinding
import com.space.shared.data.bible.BibleDetail

internal class BookAdapter(
    private val bibleDetail: BibleDetail
) : RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.itemBind(bibleDetail)
    }

    override fun getItemCount(): Int = 1

}

internal class BookViewHolder(
    private val binding: ItemBibleHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): BookViewHolder {
            val binding = ItemBibleHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return BookViewHolder(binding)
        }
    }

    fun itemBind(bibleDetail: BibleDetail) {
        binding.setVariable(BR.bible, bibleDetail)
    }
}