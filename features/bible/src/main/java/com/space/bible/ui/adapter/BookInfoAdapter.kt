package com.space.bible.ui.adapter

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.bible.databinding.ItemBibleHeaderBinding
import com.space.core_ui.databinding.ItemTextBinding
import com.space.shared.data.bible.BibleChapter

internal class BookInfoAdapter(
    private val info: List<BibleChapter>
) : RecyclerView.Adapter<BookInfoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookInfoViewHolder {
        return BookInfoViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: BookInfoViewHolder, position: Int) {
        holder.itemBind(info)
    }

    override fun getItemCount(): Int = 1

}

internal class BookInfoViewHolder(
    private val binding: ItemTextBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): BookInfoViewHolder {
            val binding = ItemTextBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return BookInfoViewHolder(binding)
        }
    }

    fun itemBind(info: List<BibleChapter>) {
        val bibleText = StringBuilder().apply {
            info.forEach {
                append("<b>${it.verse}.</b> ${it.content}. ")
            }
        }.toString()
        binding.text.text = Html.fromHtml(bibleText, Html.FROM_HTML_MODE_LEGACY)
    }
}