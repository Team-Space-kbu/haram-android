package com.space.haram_android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.space.data.model.ViewListenerModel

abstract class BaseViewModel : ViewModel() {
    private val _viewListener = MutableLiveData<ViewListenerModel>()
    val viewListener: LiveData<ViewListenerModel> = _viewListener

    val bindingViewListener = object : ViewTypeListener<Int> {
        override fun setViewType(t: Int) {
            _viewListener.value = ViewListenerModel(viewPath = t, viewStatus = true)
        }

        override fun clearViewType() {
            _viewListener.value = ViewListenerModel(viewStatus = false)
        }
    }

    val bindingKeyListener = object : KeyEventListener {
        override fun keyEvent() {
            _viewListener.value = ViewListenerModel(keyEvent = true)
        }

        override fun keyEventEnd() {
            _viewListener.value = ViewListenerModel(keyEvent = false)
        }
    }
}