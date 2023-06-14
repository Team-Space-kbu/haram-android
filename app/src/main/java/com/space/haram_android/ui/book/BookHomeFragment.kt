package com.space.haram_android.ui.book


import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentBookHomeBinding
import com.space.haram_android.ui.FunctionActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookHomeFragment : BaseFragment<FragmentBookHomeBinding>(R.layout.fragment_book_home) {

    companion object {
        fun newInstance() = BookHomeFragment()
    }

    private val viewModel: BookHomeViewModel by viewModels()

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.homeInfo.observe(viewLifecycleOwner, Observer {
            Log.d("TEST", it.toString())
        })
    }

    override fun initView() {
        super.initView()
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                return activity!!.finish()
            }
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
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, BookSearchFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
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