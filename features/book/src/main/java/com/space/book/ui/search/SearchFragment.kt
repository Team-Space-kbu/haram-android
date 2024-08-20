package com.space.book.ui.search

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.R
import com.space.book.ui.detail.DetailFragment
import com.space.book.ui.search.adapter.SearchItemAdapter
import com.space.book.ui.search.adapter.ShimmerSearchAdapter
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.view.UiHeaderAdapter
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.core_ui.util.showToast
import com.space.core_ui.extension.transformFragment
import com.space.shared.data.book.BookSearch
import com.space.shared.data.book.Category
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import com.space.shared.type.DividerType
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class SearchFragment : ContainerFragment<BookSearch>() {

    private val searchText by extraNotNull<String>("search")
        .map { it.decodeFromString<String>() }

    private val searchItemAdapter by lazy {
        SearchItemAdapter(ArrayList()) {
            parentFragmentManager.transformFragment<DetailFragment>(
                R.id.container,
                "detail" to Category.searchToCategory(it).encodeToString()
            )
        }
    }

    override val viewModel: SearchViewModel by viewModels()
    override val viewTitle: String = "도서검색"
    private var status: Boolean = false

    private var adapter: RecyclerView.Adapter<*> = ShimmerSearchAdapter()

    override fun init() {
        viewModel.getSearch(searchText)
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
    }

    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val data = viewModel.view.value?.data ?: return
        searchItemAdapter.setList(data.result)
        status = false
        if (data.start <= 1) {
            adapter = UiHeaderAdapter(
                title = "검색결과",
                titleSize = 18f,
                adapter = searchItemAdapter,
                dividerType = DividerType.GrayLine
            )
            binding.recyclerView.adapter = adapter
        }
    }

    override fun initListener() {
        super.initListener()
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, state: Int) {
                if (!binding.recyclerView.canScrollVertically(1) && state == RecyclerView.SCROLL_STATE_IDLE) {
                    val data = viewModel.view.value?.data ?: return
                    val index = data.start + 1
                    if (index <= data.end && !status) {
                        requireContext().showToast("더 많은 책을 불러옵니다.")
                        status = true
                        viewModel.getSearch(searchText, index)
                    }
                }
            }
        })
    }

}