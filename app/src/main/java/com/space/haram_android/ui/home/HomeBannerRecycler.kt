package com.space.haram_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.space.haram_android.common.data.response.home.BannerModel
import com.space.haram_android.databinding.ModelHomeBannerImgBinding


class HomeBannerRecycler : RecyclerView.Adapter<HomeBannerViewHolder>() {
    private val bannerModels: ArrayList<BannerModel> = ArrayList()

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


}

class HomeBannerViewHolder(
    private val binding: ModelHomeBannerImgBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(bannerModel: BannerModel) {
        Glide.with(itemView.context).load(bannerModel.filePath).centerCrop()
            .into(binding.homeBannerModel)
    }
}