package com.space.haram_android.adapter

import android.annotation.SuppressLint
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.haram_android.common.data.response.home.data.NewsModel
import com.space.haram_android.ui.home.HomeNewsRecycler

object BindingAdapterModel {

    @BindingAdapter("setDataList")
    @JvmStatic
    fun <T> attributeBinding(recyclerView: RecyclerView, dataList: ArrayList<T>) {

    }


}