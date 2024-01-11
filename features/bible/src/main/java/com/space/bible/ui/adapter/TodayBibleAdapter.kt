package com.space.bible.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.space.bible.BR
import com.space.bible.R
import com.space.bible.databinding.ItemBibleTextBinding
import com.space.bible.ui.adapter.view.holder.TodayEmptyViewHolder
import com.space.shared.data.bible.BibleChapter

internal class TodayBibleAdapter(
    private val bibleChapter: BibleChapter?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (bibleChapter == null){
            TodayEmptyViewHolder.newInstance(parent)
        }else{
            TodayBibleViewHolder.newInstance(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TodayBibleViewHolder){
            holder.itemBind(bibleChapter!!)
        }else{
            holder.itemView.findViewById<TextView>(R.id.today_title).text = "오늘의 성경말씀 정보가 없습니다."
        }
    }

    override fun getItemCount(): Int = 1

}

internal class TodayBibleViewHolder(
    private val binding: ItemBibleTextBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): TodayBibleViewHolder {
            val binding = ItemBibleTextBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return TodayBibleViewHolder(binding)
        }
    }

    fun itemBind(bibleChapter: BibleChapter){
        binding.setVariable(BR.today, bibleChapter)
    }
}