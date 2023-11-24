package com.space.biblemon.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.data.response.home.data.Slider
import com.space.biblemon.databinding.ModelHomeBannerImgBinding


class HomeBannerRecycler : RecyclerView.Adapter<HomeBannerRecycler.HomeBannerViewHolder>() {
    var sliders: ArrayList<Slider> = ArrayList()

    fun addItem(slider: Slider) {
        sliders.add(slider)
        notifyItemInserted(itemCount - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder =
        HomeBannerViewHolder(
            ModelHomeBannerImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = sliders.size

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) =
        holder.bindItem(sliders[position])

    inner class HomeBannerViewHolder(
        private val binding: ModelHomeBannerImgBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(slider: Slider) {
            binding.bannerModel = slider
        }
    }
}

