package com.space.biblemon.base.listener

interface ViewTypeListener<T> {

    fun setViewType(t: T)

    fun clearViewType()
}