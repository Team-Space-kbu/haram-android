package com.space.haram_android.ui.book.home


import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentBookHomeBinding
import com.space.haram_android.ui.book.info.BookDetailFragment
import com.space.haram_android.ui.book.search.BookSearchFragment
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
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.bookHomeSearch.windowToken, 0)
    }

    override fun afterObserverListener(): Unit = with(viewModel) {
        super.afterObserverListener()
        searchKeyEvent.observe(viewLifecycleOwner) {
            if (it) {
                val result = binding.bookHomeSearch.text.toString()
                setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, BookSearchFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null)
                    .commit()
                viewModel.bindingListener.keyEventEnd()
            }
        }
        viewListener.observe(viewLifecycleOwner) {
            if (it.viewStatus) {
                val result = it.viewPath
                setFragmentResult("detailKey", bundleOf("pathUrl" to result))
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, BookDetailFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null)
                    .commit()
                bindingViewListener.clearViewType()
            }
        }
    }


}