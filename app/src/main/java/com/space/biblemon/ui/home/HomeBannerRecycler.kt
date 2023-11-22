package com.space.biblemon.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.data.response.home.data.BannerModel
import com.space.biblemon.databinding.ModelHomeBannerImgBinding


class HomeBannerRecycler : RecyclerView.Adapter<HomeBannerRecycler.HomeBannerViewHolder>() {
    var bannerModels: ArrayList<BannerModel> = ArrayList()

    fun addItem(bannerModel: BannerModel) {
        bannerModels.add(bannerModel)
        notifyItemInserted(itemCount - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder =
        HomeBannerViewHolder(
            ModelHomeBannerImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = bannerModels.size

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) =
        holder.bindItem(bannerModels[position])

    inner class HomeBannerViewHolder(
        private val binding: ModelHomeBannerImgBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(bannerModel: BannerModel) {
            binding.bannerModel = bannerModel
        }
    }
}

