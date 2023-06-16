package com.space.haram_android.ui.book


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentBookHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookHomeFragment : BaseFragment<FragmentBookHomeBinding>(R.layout.fragment_book_home) {

    companion object {
        fun newInstance() = BookHomeFragment()
    }

    private val viewModel: BookHomeViewModel by viewModels()
    private lateinit var bestCategory: BookCategoryRecycler
    private lateinit var newCategory: BookCategoryRecycler

    @SuppressLint("NotifyDataSetChanged")
    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.homeInfo.observe(viewLifecycleOwner, Observer {
            it?.bestBook?.forEach { i -> bestCategory.addItem(i) }
            it?.newBook?.forEach { i -> newCategory.addItem(i) }
            bestCategory.notifyDataSetChanged()
            newCategory.notifyDataSetChanged()

        })
    }

    override fun initView() {
        super.initView()
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                return activity!!.finish()
            }
        }
        bestCategory = BookCategoryRecycler()
        newCategory = BookCategoryRecycler()
        binding.bookHomeBestRecycler.apply {
            adapter = bestCategory
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
        binding.bookHomeNewRecycler.apply {
            adapter = newCategory
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
        activity?.findViewById<TextView>(R.id.function_toolbar_title)?.text = "도서검색"
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun initListener() = with(binding) {
        super.initListener()
        bookHomeBackground.setOnClickListener {
            keyboardDown(bookHomeBackground)
        }
        bookHomeSearch.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                keyboardDown(bookHomeSearch)
                val result = bookHomeSearch.text.toString()
                setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, BookSearchFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null)
                    .commit()
                return@OnKeyListener true
            }
            false
        })
    }

    private fun keyboardDown(view: View) {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}