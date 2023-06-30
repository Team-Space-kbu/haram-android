package com.space.haram_android.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
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
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
        }
        if (item != null) {
            (recyclerView.adapter as HomeNewsRecycler).newsModels = item as ArrayList<NewsModel>
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }


    @JvmStatic
    @BindingAdapter(value = ["setViewEvent", "viewType"])
    fun setViewItems(textView: TextView, event: ViewEventTypeInf, viewType: ViewType) {
        textView.setOnClickListener {
            event.setViewType(viewType)
        }
    }


    @JvmStatic
    @BindingAdapter(value = ["offsetPx", "sliderHandler", "sliderImageRunnable"])
    fun setSlider(
        viewPager2: ViewPager2,
        offsetPx: Int,
        sliderHandler: Handler,
        sliderImageRunnable: Runnable
    ) {
        viewPager2.offscreenPageLimit = 1
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderImageRunnable)
                sliderHandler.postDelayed(sliderImageRunnable, 3000)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
        viewPager2.setPageTransformer { view, position ->
            view.translationX = position * -offsetPx
        }
    }

}