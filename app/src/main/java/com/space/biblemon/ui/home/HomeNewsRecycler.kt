package com.space.biblemon.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.data.res.home.data.NewsModel
import com.space.biblemon.databinding.ModelHomeNewsImgBinding


class HomeNewsRecycler : RecyclerView.Adapter<NewsViewHolder>() {
    var newsModels: ArrayList<NewsModel> = ArrayList()

    fun addItem(newsModel: NewsModel) {
        newsModels.add(newsModel)
        notifyItemInserted(itemCount - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            ModelHomeNewsImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = newsModels.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.bindItem(newsModels[position])


}

class NewsViewHolder(
    private val binding: ModelHomeNewsImgBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(newsModel: NewsModel) {
        binding.newsModel = newsModel
    }
}