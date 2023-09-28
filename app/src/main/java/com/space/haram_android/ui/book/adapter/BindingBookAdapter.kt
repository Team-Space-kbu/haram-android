package com.space.haram_android.ui.book.adapter

import android.annotation.SuppressLint
import androidx.core.view.isEmpty
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.data.res.book.data.CategoryModel
import com.space.data.res.book.data.KeepInfoModel
import com.space.data.res.book.data.SearchResultModel
import com.space.data.type.ListViewType
import com.space.haram_android.R
import com.space.haram_android.base.ViewTypeListener
import com.space.haram_android.ui.book.home.BookCategoryRecycler
import com.space.haram_android.ui.book.info.BookDetailKeepRecycler
import com.space.haram_android.ui.book.search.BookSearchRecycler
import com.space.haram_android.util.DividerItemDecoration

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
        val adapter = recyclerView.adapter as? BookSearchRecycler ?: return
        val list = item ?: return
        adapter.let {
            it.addItem(list)
            item.clear()
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

    @JvmStatic
    @BindingAdapter("listViewType")
    fun setViewType(
        recyclerView: RecyclerView, type: ListViewType
    ) {
        recyclerView.setHasFixedSize(true)
        when (type) {
            ListViewType.BOOK_HORIZONTAL -> {
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
            }

            ListViewType.BOOK_VERTICAL -> {
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

            else -> {
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
            }
        }


    }


}