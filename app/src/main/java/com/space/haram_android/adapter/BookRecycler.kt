package com.space.haram_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.haram_android.databinding.ModelBookSearchLayoutBinding


class BookRecycler<M> : RecyclerView.Adapter<SearchViewHolder<M>>() {
    private var models: ArrayList<M> = ArrayList()

    fun addList(model: ArrayList<M> ) {
        models = model
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder<M> =
        SearchViewHolder(
            ModelBookSearchLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: SearchViewHolder<M>, position: Int) =
        holder.bindItem(models[position])

}

class SearchViewHolder<M>(
    private val binding: ModelBookSearchLayoutBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(model: M) {

    }
}