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

    override fun initView() {
        super.initView()
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                return activity!!.finish()
            }
        }
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        activity?.findViewById<TextView>(R.id.function_toolbar_title)?.text = "도서검색"
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun initListener() = with(binding) {
        super.initListener()
        bookHomeSearch.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(bookHomeSearch.windowToken, 0)
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
        bookHomeBackground.setOnClickListener {
            val imm =
                it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

}