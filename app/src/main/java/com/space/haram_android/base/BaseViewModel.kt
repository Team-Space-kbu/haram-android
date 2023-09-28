package com.space.haram_android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.space.data.model.BookCategoryView

abstract class BaseViewModel : ViewModel() {
    private val _viewListener = MutableLiveData<BookCategoryView>()
    val viewListener: LiveData<BookCategoryView> = _viewListener

    val bindingViewListener = object : ViewTypeListener<Int> {
        override fun setViewType(t: Int) {
            _viewListener.value = BookCategoryView(viewPath = t, viewStatus = true)
        }

        override fun clearViewType() {
            _viewListener.value = BookCategoryView(viewStatus = false)
        }
    }

    val bindingKeyListener = object : KeyEventListener {
        override fun keyEvent() {
            _viewListener.value = BookCategoryView(keyEvent = true)
        }

        override fun keyEventEnd() {
            _viewListener.value = BookCategoryView(keyEvent = false)
        }
    }
}