package com.space.haram_android.ui.book.search


import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.space.haram_android.BR
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentBookSearchBinding
import com.space.haram_android.ui.book.info.BookDetailFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookSearchFragment : BaseFragment<FragmentBookSearchBinding>(R.layout.fragment_book_search) {

    companion object {
        fun newInstance() = BookSearchFragment()
    }

    private val viewModel: BookSearchViewModel by viewModels()

    override fun initView() {
        super.initView()
        setFragmentResultListener("requestKey") { _, bundle ->
            val result = bundle.getString("bundleKey")
            result?.let { viewModel.getSearchList(it) }
        }
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        activity?.findViewById<TextView>(R.id.function_toolbar_title)?.text = "도서 검색"
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        viewListener.observe(viewLifecycleOwner) {
            if (it.viewStatus) {
                val result = it.viewPath
                setFragmentResult("detailKey", bundleOf("pathUrl" to result))
                parentFragmentManager.commit {
                    replace(R.id.container, BookDetailFragment())
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    addToBackStack(null)
                }
                bindingViewListener.clearViewType()
            }
        }
    }


}

