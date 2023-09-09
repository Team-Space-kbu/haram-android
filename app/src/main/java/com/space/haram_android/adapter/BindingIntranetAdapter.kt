package com.space.haram_android.adapter

import android.annotation.SuppressLint

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.data.res.intranet.ChapelListRes
import com.space.haram_android.ui.chapel.ChapelListRecycler


object BindingIntranetAdapter {

    @JvmStatic
    @BindingAdapter("setChapel")
    @SuppressLint("NotifyDataSetChanged")
    fun setNewsItems(recyclerView: RecyclerView, item: List<ChapelListRes>?) {
        if (recyclerView.adapter == null) {
            val adapter = item?.let { ChapelListRecycler() }
            recyclerView.isNestedScrollingEnabled = false
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
            recyclerView.adapter = adapter
        }
        if (item != null) {
            (recyclerView.adapter as ChapelListRecycler).listRes = item as ArrayList<ChapelListRes>
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }
}