package com.space.biblemon.ui.book.search


import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.space.biblemon.BR
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentBookSearchBinding
import com.space.biblemon.util.FragmentFactory
import com.space.biblemon.util.ViewType
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class BookSearchFragment : BaseFragment<FragmentBookSearchBinding>(R.layout.fragment_book_search) {

    companion object {
        fun newInstance() = BookSearchFragment()
    }

    private val viewModel: BookSearchViewModel by viewModels()
    private lateinit var searchAdapter: BookSearchRecycler;

    override fun init() {
        super.init()
        searchAdapter = BookSearchRecycler(viewModel.bindingViewListener)
        toolbarTitle = "도서검색"
        setFragmentResultListener("requestKey") { _, bundle ->
            val result = bundle.getString("bundleKey")
            result?.let {
                viewModel.setInputText(it)
                viewModel.getSearchList()
            }
        }
    }

    override fun initView() {
        super.initView()
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.searchList.adapter = searchAdapter
    }

    override fun initListener() {
        super.initListener()
        binding.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, _, _, _ ->
            viewModel.searchForm.value?.let {
                if (!v.canScrollVertically(1) && (it.searchReq!!.start + 1 <= it.searchReq.end)) {
                    Toast.makeText(context, "더 많은 정보를 불러오고 있습니다.", Toast.LENGTH_SHORT).show()
                    viewModel.getSearchList(
                        it.searchReq.start + 1
                    )

                }
            }
        })
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        viewListener.observe(viewLifecycleOwner) {
            if (it.viewStatus) {
                val result = it.viewPath
                setFragmentResult("detailKey", bundleOf("pathUrl" to result))
                newFragmentView(FragmentFactory.createFragment(ViewType.BOOK_DETAIL)!!)
                bindingViewListener.clearViewType()
            }
        }
    }


}
