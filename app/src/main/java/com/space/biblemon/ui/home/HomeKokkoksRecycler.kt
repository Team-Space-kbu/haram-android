package com.space.biblemon.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.biblemon.base.listener.onClickEventListener
import com.space.biblemon.databinding.ModelHomeNewsImgBinding
import com.space.data.response.home.data.Kokkos


class HomeNewsRecycler(
    val onClick : onClickEventListener<String>
) : RecyclerView.Adapter<HomeNewsRecycler.NewsViewHolder>() {
    var kokkos: ArrayList<Kokkos> = ArrayList()

    fun addItem(kokkos: Kokkos) {
        kokkos.add(kokkos)
        notifyItemInserted(itemCount - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            ModelHomeNewsImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = kokkos.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.bindItem(kokkos[position])


    inner class NewsViewHolder(
        private val binding: ModelHomeNewsImgBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(kokkos: Kokkos) {
            binding.newsModel = kokkos
            binding.homeNewsImage.setOnClickListener {

                onClick.apply(kokkos.file)
            }
        }

    }
}

