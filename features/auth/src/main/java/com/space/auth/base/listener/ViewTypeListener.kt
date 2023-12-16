package com.space.auth.base.listener

interface ViewTypeListener<T> {

    fun setViewType(t: T)

    fun clearViewType()
}