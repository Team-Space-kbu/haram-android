package com.space.haram_android.adapter

import android.annotation.SuppressLint
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.data.response.book.CategoryModel
import com.space.data.response.book.data.BookKeepInfo
import com.space.data.response.book.data.SearchResultModel
import com.space.haram_android.R
import com.space.haram_android.ui.book.home.BookCategoryRecycler
import com.space.haram_android.ui.book.info.BookDetailKeepRecycler
import com.space.haram_android.ui.book.search.BookSearchRecycler
import com.space.haram_android.util.DividerItemDecoration

object BindingBookAdapter {

    @JvmStatic
    @BindingAdapter(value = ["bookHomeAdapter", "bookHomeListener"])
    @SuppressLint("NotifyDataSetChanged")
    fun setBookItems(
        recyclerView: RecyclerView,
        item: List<CategoryModel>?,
        viewListener: BookViewListener
    ) {
        if (recyclerView.adapter == null) {
            val adapter = item?.let { BookCategoryRecycler(viewListener) }
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
            recyclerView.adapter = adapter
        }
        if (item != null) {
            (recyclerView.adapter as BookCategoryRecycler).categoryModels =
                item as ArrayList<CategoryModel>
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }


    @JvmStatic
    @BindingAdapter(value = ["bookSearchAdapter", "bookSearchListener"])
    @SuppressLint("NotifyDataSetChanged")
    fun setSearchItems(
        recyclerView: RecyclerView,
        item: List<SearchResultModel>?,
        viewListener: BookViewListener
    ) {
        if (recyclerView.adapter == null) {
            val adapter = item?.let { BookSearchRecycler(viewListener) }
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    R.drawable.line_divider,
                    10,
                    10
                )
            )
            recyclerView.adapter = adapter
            recyclerView.isNestedScrollingEnabled = false
            recyclerView.itemAnimator = null
        }
        if (item != null) {
            (recyclerView.adapter as BookSearchRecycler).models =
                item as ArrayList<SearchResultModel>
            recyclerView.adapter?.notifyDataSetChanged()
        }

    }


    @JvmStatic
    @BindingAdapter("bookDetailAdapter")
    @SuppressLint("NotifyDataSetChanged")
    fun setDetailItems(recyclerView: RecyclerView, item: List<BookKeepInfo>?) {
        if (recyclerView.adapter == null) {
            val adapter = item?.let { BookDetailKeepRecycler() }
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    R.drawable.line_divider,
                    50,
                    50
                )
            )
            recyclerView.isNestedScrollingEnabled = false
        }
        if (item != null) {
            (recyclerView.adapter as BookDetailKeepRecycler).models =
                item as ArrayList<BookKeepInfo>
            recyclerView.adapter?.notifyDataSetChanged()
            recyclerView.setItemViewCacheSize(10)
        }

    }


}