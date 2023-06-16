package com.space.haram_android.ui.book


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentBookSearchBinding
import com.space.haram_android.util.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookSearchFragment : BaseFragment<FragmentBookSearchBinding>(R.layout.fragment_book_search) {

    companion object {
        fun newInstance() = BookSearchFragment()
    }

    private val viewModel: BookSearchViewModel by viewModels()
    private lateinit var searchRecycler: BookSearchRecycler

    override fun initView() {
        super.initView()
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            result?.let { viewModel.getSearchList(it) }
        }
        searchRecycler = BookSearchRecycler()
        binding.bookSearchListRecycler.apply {
            adapter = searchRecycler
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, R.drawable.line_divider, 50, 50))
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.searchForm.observe(viewLifecycleOwner, Observer {
            it?.forEach { i -> searchRecycler.addItem(i) }
            searchRecycler.notifyDataSetChanged()
        })
    }


}

