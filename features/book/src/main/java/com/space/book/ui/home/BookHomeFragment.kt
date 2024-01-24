package com.space.book.ui.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.book.ui.common.BookAdapter
import com.space.book.ui.detail.DetailFragment
import com.space.book.ui.home.adapter.SearchAdapter
import com.space.book.ui.home.adapter.SliderAdapter
import com.space.book.ui.search.SearchFragment
import com.space.core_ui.BR
import com.space.core_ui.ParamsItemHandler
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.transformFragment
import com.space.shared.data.BookItem
import com.space.shared.data.book.Category
import com.space.shared.encodeToString
import com.space.core_ui.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookHomeFragment : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    companion object {
        fun newInstance() = BookHomeFragment()
    }

    private val viewModel: BookHomeViewModel by viewModels()


    private val handler = ParamsItemHandler<Category> { category ->
        parentFragmentManager.transformFragment<DetailFragment>(
            R.id.container,
            "detail" to category.encodeToString()
        )
    }

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title,"도서검색")
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initListener() {
        super.initListener()
        viewModel.bookHome.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                SearchAdapter() { text ->
                    parentFragmentManager.transformFragment<SearchFragment>(
                        R.id.container,
                        "search" to text.encodeToString()
                    )
                },
                SliderAdapter(it.image) {
                    //TODO 공지사항 클릭시 발생하는 이벤트
                },
                BookAdapter(BookItem("인기도서", it.bestBook), handler),
                BookAdapter(BookItem("신작도서", it.newBook), handler),
                BookAdapter(BookItem("대여정보", it.rentalBook), handler)
            )
            binding.recyclerView.adapter = adapter
        }
    }

}