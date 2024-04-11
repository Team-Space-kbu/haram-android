package com.space.core_ui.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.InputType
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.space.core_ui.EditType
import com.space.core_ui.NonParamsItemHandler
import com.space.core_ui.util.dateToDateTime
import com.space.shared.type.LayoutType
import com.space.shared.util.formatToDate
import timber.log.Timber

@BindingAdapter("setImgFull")
fun setImageFull(
    imageView: ImageView,
    url: String?
) {
    url?.let {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}

@BindingAdapter("setImgUrl")
fun setImageUrl(
    imageView: ImageView,
    url: String?
) {
    url?.let {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
}

@BindingAdapter("setImgUrl")
fun setImageUrl(
    imageView: ImageView,
    url: Drawable?
) {
    url?.let {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
}

@BindingAdapter("setImgUrl")
fun setImageUrl(
    imageView: ImageView,
    bitmap: Bitmap?
) {
    bitmap?.let {
        Glide.with(imageView.context)
            .load(bitmap)
            .centerCrop()
            .into(imageView)
    }
}


@BindingAdapter("layoutType")
fun setLayoutType(
    recyclerView: RecyclerView,
    type: LayoutType
) {
    recyclerView.setHasFixedSize(true)
    when (type) {
        LayoutType.HORIZONTAL -> {
            recyclerView.layoutManager =
                LinearLayoutManager(
                    recyclerView.context,
                    RecyclerView.HORIZONTAL,
                    false
                )
        }

        LayoutType.VERTICAL -> {
            recyclerView.layoutManager =
                LinearLayoutManager(
                    recyclerView.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }

        else -> {
            recyclerView.layoutManager =
                LinearLayoutManager(
                    recyclerView.context,
                    RecyclerView.VERTICAL,
                    false
                )
        }
    }
}

@BindingAdapter("setDate")
fun setDate(
    textView: TextView,
    date: String? = "",
) {
    try {
        date?.let {
            textView.text = formatToDate(date)
        }
    } catch (e: Exception) {
        Timber.d(e.message)
        textView.text = "정보 없음"
    }
}

@BindingAdapter("setDateTime")
fun setDateTime(
    textView: TextView,
    date: String? = "",
) {
    try {
        date?.let {
            textView.text = dateToDateTime(it)
        }
    } catch (e: Exception) {
        Timber.d(e.message)
        textView.text = "정보없음"
    }
}
@BindingAdapter("setEditorAction", "editorActionHandler")
fun setEditText(
    editText: EditText,
    editorAction: Boolean,
    nonParamsItemHandler: NonParamsItemHandler
) {
    val inputMethodManager =
        editText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (editorAction) {
        editText.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)
                nonParamsItemHandler.onClick()
                return@setOnKeyListener true
            }
            false
        }
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)
                nonParamsItemHandler.onClick()
                return@setOnEditorActionListener true
            }
            false
        }
    }
}


@BindingAdapter("setEditType")
fun setEditType(
    editText: EditText,
    editType: EditType
) {
    editText.inputType = when (editType) {
        EditType.TEXT ->
            InputType.TYPE_CLASS_TEXT

        EditType.ID ->
            InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

        EditType.NAME ->
            InputType.TYPE_TEXT_VARIATION_PERSON_NAME

        EditType.PHONE ->
            InputType.TYPE_CLASS_PHONE

        EditType.PASSWORD ->
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        EditType.NUMBER ->
            InputType.TYPE_CLASS_NUMBER

        EditType.MULTI_LINE ->
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE

        else -> {
            InputType.TYPE_CLASS_TEXT
        }
    }
}
