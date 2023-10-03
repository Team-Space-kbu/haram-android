package com.space.biblemon.base.adapter

import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.space.biblemon.R
import com.space.biblemon.base.listener.KeyEventListener
import com.space.biblemon.util.DividerItemDecoration
import com.space.data.type.ListViewType


object BindingAdapterModel {

    @JvmStatic
    @BindingAdapter("lowerKeyboardOnClick")
    fun View.lowerKeyboardOnClick(lower: Boolean) {
        if (lower) {
            setOnClickListener {
                val inputMethodManager =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("onKeyboardEnterAction")
    fun setOnKeyboardActionEnterListener(
        view: EditText,
        event: KeyEventListener,
    ) {
        view.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP) {
                val inputMethodManager =
                    view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                event.keyEvent()
            }
            event.keyEventEnd()
            false
        }
    }

    @JvmStatic
    @BindingAdapter("onKeyboardDoneAction")
    fun setOnKeyboardActionDoneListener(
        view: EditText,
        event: KeyEventListener,
    ) {
        view.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val inputMethodManager =
                    view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                event.keyEvent()
            }
            event.keyEventEnd()
            false
        }
    }

    @JvmStatic
    @BindingAdapter("loginStatus")
    fun setLoginStatus(textView: TextView, boolean: Boolean) {
        textView.visibility = if (boolean) View.GONE else View.VISIBLE
    }


    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView.context)
                .load(url)
                .centerCrop()
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("listViewType")
    fun setViewType(
        recyclerView: RecyclerView, type: ListViewType
    ) {
        recyclerView.setHasFixedSize(true)
        when (type) {
            ListViewType.HORIZONTAL -> {
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
            }

            ListViewType.VERTICAL -> {
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
                recyclerView.addItemDecoration(
                    DividerItemDecoration(
                        recyclerView.context,
                        R.drawable.line_divider,
                        5,
                        5
                    )
                )
                recyclerView.isNestedScrollingEnabled = false
            }

            else -> {
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
            }
        }
    }


}