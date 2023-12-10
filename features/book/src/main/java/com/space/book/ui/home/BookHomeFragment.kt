package com.space.book.ui.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.book.ui.common.BookAdapter
import com.space.book.ui.common.BookItemAdapter
import com.space.book.ui.detail.DetailFragment
import com.space.book.ui.home.adapter.SearchAdapter
import com.space.book.ui.home.adapter.SliderAdapter
import com.space.book.ui.search.SearchFragment
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.transformFragment
import com.space.shared.data.Item
import com.space.shared.data.book.Category
import com.space.shared.encodeToString
import com.space.core_ui.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookHomeFragment :
    BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    companion object {
        fun newInstance() = BookHomeFragment()
    }

    private val viewModel: BookHomeViewModel by viewModels()


    private val detail = object : BookItemAdapter.ItemHandler {
        override fun clickCategory(category: Category) {
            parentFragmentManager.transformFragment<DetailFragment>(
                R.id.container,
                "detail" to category.encodeToString()
            )
        }
    }

    private val search = object : SearchAdapter.ItemHandler {
        override fun inputSearch(string: String) {
            parentFragmentManager.transformFragment<SearchFragment>(
                R.id.container,
                "search" to string.encodeToString()
            )
        }
    }


    override fun initView() {
        super.initView()
        binding.titleToolbar.text = "도서검색"
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initListener() {
        super.initListener()
        viewModel.bookHome.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                SearchAdapter(search),
                SliderAdapter(
                    Item(
                        list = it.image,
                    )
                ),
                BookAdapter(
                    Item(
                        title = "인기도서",
                        list = it.bestBook,
                        event = detail
                    )
                ),
                BookAdapter(
                    Item(
                        title = "신작도서",
                        list = it.newBook,
                        event = detail

                    )
                ),
                BookAdapter(
                    Item(
                        title = "대여정보",
                        list = it.rentalBook,
                        event = detail
                    )
                )
            )
            binding.recyclerView.adapter = adapter
        }
    }

}