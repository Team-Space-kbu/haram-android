package com.space.haram_android.ui.book.search


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
import timber.log.Timber


@AndroidEntryPoint
class BookSearchFragment : BaseFragment<FragmentBookSearchBinding>(R.layout.fragment_book_search) {

    companion object {
        fun newInstance() = BookSearchFragment()
    }

    private val viewModel: BookSearchViewModel by viewModels()
    private lateinit var searchAdapter: BookSearchRecycler

    override fun initView() {
        super.initView()
        toolbarTitle = "도서검색"
        setFragmentResultListener("requestKey") { _, bundle ->
            val result = bundle.getString("bundleKey")
            result?.let { viewModel.getSearchList(it) }
        }
        searchAdapter = BookSearchRecycler(viewModel.bindingViewListener)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.searchList.adapter = searchAdapter
    }

    override fun initListener() {
        super.initListener()
//        binding.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, _, _, _ ->
//            if (!v.canScrollVertically(1)) {
//                Toast.makeText(context, "더 많은 정보를 불러오고 있습니다.", Toast.LENGTH_SHORT).show()
//                viewModel.getSearchList(
//                    "java", viewModel.searchForm.value!!.searchReq.start + 1
//                )
//            }
//        })
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
