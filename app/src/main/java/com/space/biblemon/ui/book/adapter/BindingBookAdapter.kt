package com.space.biblemon.ui.book.adapter

import android.annotation.SuppressLint
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import response.book.data.CategoryModel
import response.book.data.KeepInfoModel
import response.book.data.SearchResultModel
import com.space.biblemon.base.listener.ViewTypeListener
import com.space.biblemon.ui.book.home.BookBannerRecycler
import com.space.biblemon.ui.book.home.BookCategoryRecycler
import com.space.biblemon.ui.book.info.BookDetailKeepRecycler
import com.space.biblemon.ui.book.search.BookSearchRecycler

@SuppressLint("NotifyDataSetChanged")
object BindingBookAdapter {

    @JvmStatic
    @BindingAdapter(value = ["bookCategoryAdapter", "bookCategoryListener"])
    fun setBookItems(
        recyclerView: RecyclerView,
        item: MutableList<CategoryModel>?,
        viewListener: ViewTypeListener<Int>
    ) {
        recyclerView.adapter ?: run {
            recyclerView.adapter = BookCategoryRecycler(viewListener)
        }
        val list = item ?: return
        recyclerView.adapter.let {
            if (it is BookCategoryRecycler) {
                it.addItem(list)
                it.notifyDataSetChanged()
            }
        }
    }


    @JvmStatic
    @BindingAdapter(value = ["bookSearchAdapter"])
    fun setSearchItems(
        recyclerView: RecyclerView,
        item: MutableList<SearchResultModel>?,
    ) {
        val list = item ?: return
        val adapter = recyclerView.adapter as? BookSearchRecycler ?: return
        adapter.let {
            it.addItem(list)
            item.clear()
        }

    }

    @JvmStatic
    @BindingAdapter("bookDetailAdapter")
    fun setSearchImageItems(
        recyclerView: RecyclerView,
        item: MutableList<String>?
    ) {
        recyclerView.adapter ?: run {
            recyclerView.adapter = BookBannerRecycler()
        }
        recyclerView.adapter.let {
            if (it is BookBannerRecycler) {
                item?.let { it1 -> it.addItem(it1) }
                it.notifyDataSetChanged()
            }
        }
    }


    @JvmStatic
    @BindingAdapter("bookDetailAdapter")
    fun setDetailItems(
        recyclerView: RecyclerView,
        item: MutableList<KeepInfoModel>?
    ) {
        recyclerView.adapter ?: run {
            recyclerView.adapter = BookDetailKeepRecycler()
        }
        recyclerView.adapter.let {
            if (it is BookDetailKeepRecycler) {
                item?.let { it1 -> it.addItem(it1) }
                it.notifyDataSetChanged()
            }
        }
    }

}