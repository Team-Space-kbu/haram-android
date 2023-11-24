package com.space.biblemon.ui.home

import android.annotation.SuppressLint
import android.os.Handler
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.space.biblemon.util.ViewType
import com.space.data.response.home.data.Slider
import com.space.data.response.home.data.Kokkos
import com.space.biblemon.base.listener.ViewTypeListener

object BindingHomeAdapter {

//    @JvmStatic
//    @BindingAdapter("setBookHomeItems")
//    @SuppressLint("NotifyDataSetChanged")
//    fun setBookItems(recyclerView: RecyclerView, item: List<CategoryModel>?) {
//        if (item != null) {
//            (recyclerView.adapter as BookCategoryRecyclerView).setItem(item)
//        }
//        recyclerView.adapter?.notifyDataSetChanged()
//    }


    @JvmStatic
    @BindingAdapter("homeBanner")
    @SuppressLint("NotifyDataSetChanged")
    fun setBannerItems(viewPager2: ViewPager2, item: List<Slider>?) {
        if (viewPager2.adapter == null) {
            val adapter = item?.let { HomeBannerRecycler() }
            viewPager2.adapter = adapter
        }
        if (item != null) {
            (viewPager2.adapter as HomeBannerRecycler).sliders =
                item as ArrayList<Slider>
        }
        viewPager2.adapter?.notifyDataSetChanged()
    }


    @JvmStatic
    @BindingAdapter(value = ["homeNews"])
    @SuppressLint("NotifyDataSetChanged")
    fun setNewsItems(recyclerView: RecyclerView, item: List<Kokkos>? ) {
        recyclerView.layoutManager =
            LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
        if (item != null) {
            (recyclerView.adapter as HomeNewsRecycler).kokkos = item as ArrayList<Kokkos>
            (recyclerView.adapter as HomeNewsRecycler).notifyDataSetChanged()
        }

    }


    @JvmStatic
    @BindingAdapter(value = ["setViewEvent", "viewType"])
    fun setViewItems(textView: TextView, event: ViewTypeListener<ViewType>, viewType: ViewType) {
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