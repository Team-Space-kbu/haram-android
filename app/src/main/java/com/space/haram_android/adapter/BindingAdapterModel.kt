package com.space.haram_android.adapter

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide

object BindingAdapterModel {
    @JvmStatic
    @BindingAdapter("lowerKeyboardOnClick")
    fun View.lowerKeyboardOnClick(lower: Boolean) {
        if (lower) {
            setOnClickListener {
                val inputMethodManager =
                    context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
            }
        }

    }

    @JvmStatic
    @BindingAdapter("onKeyboardEnterAction")
    fun setOnKeyboardActionEnterListener(
        view: EditText,
        event: MutableLiveData<Boolean>,
    ) {
        view.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP) {
                val inputMethodManager =
                    view.context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                event.value = true
                return@setOnKeyListener true
            }
            event.value = false
            return@setOnKeyListener false
        }
    }

    @JvmStatic
    @BindingAdapter("onKeyboardDoneAction")
    fun setOnKeyboardActionDoneListener(
        view: EditText,
        event: MutableLiveData<Boolean>,
    ) {
        view.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val inputMethodManager =
                    view.context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                event.value = true
                return@setOnEditorActionListener true
            }
            event.value = false
            return@setOnEditorActionListener false
        }
    }


    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageView: ImageView, url: String?) {
        url.let {
            Glide.with(imageView.context)
                .load(url)
                .centerCrop()
                .into(imageView)
        }
    }

}