package com.space.haram_android.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.space.haram_android.common.data.ViewType
import com.space.haram_android.common.data.response.home.data.BannerModel
import com.space.haram_android.common.data.response.home.data.NewsModel
import com.space.haram_android.ui.FunctionActivity
import com.space.haram_android.ui.home.HomeBannerRecycler
import com.space.haram_android.ui.home.HomeNewsRecycler

object BindingHomeAdapter {


    @JvmStatic
    @BindingAdapter("homeBanner")
    @SuppressLint("NotifyDataSetChanged")
    fun setBannerItems(viewPager2: ViewPager2, item: List<BannerModel>?) {
        if (viewPager2.adapter == null) {
            val adapter = item?.let { HomeBannerRecycler() }
            viewPager2.adapter = adapter
        }
        if (item != null) {
            (viewPager2.adapter as HomeBannerRecycler).bannerModels =
                item as ArrayList<BannerModel>
        }
        viewPager2.adapter?.notifyDataSetChanged()
    }


    @JvmStatic
    @BindingAdapter("homeNews")
    @SuppressLint("NotifyDataSetChanged")
    fun setNewsItems(recyclerView: RecyclerView, item: List<NewsModel>?) {
        if (recyclerView.adapter == null) {
            val adapter = item?.let { HomeNewsRecycler() }
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
            recyclerView.adapter = adapter
        }
        if (item != null) {
            (recyclerView.adapter as HomeNewsRecycler).newsModels = item as ArrayList<NewsModel>
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }

    @JvmStatic
    @BindingAdapter("chapel")
    fun setItems(textView: TextView, int: Int) {
        textView.setOnClickListener {
            val intent = Intent(textView.context, FunctionActivity::class.java)
            intent.apply {
                this.putExtra("viewType", ViewType.INTRANET_CHAPEL)
            }
            textView.context.startActivity(intent)
        }
    }

}