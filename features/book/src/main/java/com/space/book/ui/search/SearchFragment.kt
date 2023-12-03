package com.space.book.ui.search

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.book.R
import com.space.book.databinding.FragmentBookContainerBinding
import com.space.book.ui.common.BookItemAdapter
import com.space.book.ui.detail.DetailFragment
import com.space.book.ui.search.adapter.SearchAdapter
import com.space.book.ui.search.adapter.SearchItemAdapter
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.transformFragment
import com.space.shared.data.book.Category
import com.space.shared.data.book.Search
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentBookContainerBinding>(R.layout.fragment_book_container) {

    private val searchText by extraNotNull<String>("search")
        .map { encodeString ->
            encodeString.decodeFromString<String>()
        }

    private val click = object : SearchItemAdapter.ItemHandler {
        override fun clickSearch(search: Search) {
            val category = Category(
                path = search.path,
                image = search.image,
                title = search.title
            )
            parentFragmentManager.transformFragment<DetailFragment>(
                R.id.container,
                "detail" to category.encodeToString()
            )
        }
    }

    private val viewModel: SearchViewModel by viewModels()

    override fun init() {
        super.init()
        viewModel.getSearch(searchText)
    }

    override fun initView() {
        super.initView()
        binding.titleToolbar.text = "도서검색"
    }

    override fun initListener() {
        super.initListener()
        viewModel.searchInfo.observe(viewLifecycleOwner){
            val adapter = ConcatAdapter(
                SearchAdapter(),
                SearchItemAdapter(it.result, click)
            )
            binding.recyclerView.adapter = adapter
        }
    }

}