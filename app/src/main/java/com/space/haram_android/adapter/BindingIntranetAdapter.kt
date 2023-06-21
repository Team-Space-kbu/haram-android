package com.space.haram_android.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface.OnKeyListener
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.haram_android.common.data.response.home.data.NewsModel
import com.space.haram_android.common.data.response.intranet.ChapelListRes
import com.space.haram_android.ui.chapel.ChapelListRecycler
import com.space.haram_android.ui.home.HomeNewsRecycler


object BindingIntranetAdapter {

    @JvmStatic
    @BindingAdapter("onClickFragment")
    fun setBannerItems(view: View, listener: View.OnClickListener) {

    }

    @JvmStatic
    @BindingAdapter("loginStatus")
    fun setLoginStatus(textView: TextView, boolean: Boolean) {
        textView.visibility = if (boolean) View.GONE else View.VISIBLE
    }

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