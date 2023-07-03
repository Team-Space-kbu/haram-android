package com.space.haram_android.adapter

interface ViewTypeListener<T> {

    fun setViewType(viewType: T)

    fun clearViewType()
}