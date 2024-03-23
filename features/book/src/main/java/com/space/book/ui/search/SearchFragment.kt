package com.space.book.ui.search

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.R
import com.space.book.ui.detail.DetailFragment
import com.space.book.ui.search.adapter.SearchHeaderAdapter
import com.space.book.ui.search.adapter.SearchItemAdapter
import com.space.book.ui.search.adapter.ShimmerSearchAdapter
import com.space.core_ui.BR
import com.space.core_ui.DividerItemDecoration
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.showToast
import com.space.core_ui.transformFragment
import com.space.shared.data.book.Category
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    private val searchText by extraNotNull<String>("search")
        .map { encodeString ->
            encodeString.decodeFromString<String>()
        }

    private val searchItemAdapter by lazy {
        SearchItemAdapter(ArrayList()) {
            parentFragmentManager.transformFragment<DetailFragment>(
                R.id.container,
                "detail" to Category.searchToCategory(it).encodeToString()
            )
        }
    }

    private val viewModel: SearchViewModel by viewModels()
    private var status: Boolean = false

    override fun init() {
        viewModel.getSearch(searchText)
    }

    override fun beforeObserverListener() {
        viewModel.searchInfo.observe(this) {
            searchItemAdapter.setList(it.result)
            status = false
            if (it.start <= 1) {
                binding.recyclerView.adapter = ConcatAdapter(
                    SearchHeaderAdapter(),
                    searchItemAdapter
                )
            }
        }
    }

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title, "도서검색")
        binding.lifecycleOwner = viewLifecycleOwner
        if (viewModel.searchInfo.isInitialized) {
            searchItemAdapter.let {
                binding.recyclerView.adapter =
                    ConcatAdapter(
                        SearchHeaderAdapter(),
                        searchItemAdapter
                    )
            }
        } else {
            binding.recyclerView.adapter = ShimmerSearchAdapter()
        }
    }

    override fun initListener() {
        super.initListener()
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, state: Int) {
                if (!binding.recyclerView.canScrollVertically(1) && state == RecyclerView.SCROLL_STATE_IDLE) {
                    val data = viewModel.searchInfo.value?: return
                    val index = data.start + 1
                    if (index <= data.end + 1 && !status) {
                        requireContext().showToast("더 많은 책을 불러옵니다.")
                        status = true
                        viewModel.getSearch(searchText, index)
                    }
                }
            }
        })
    }

}