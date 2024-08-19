package com.space.book.ui.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.book.ui.common.BookAdapter
import com.space.book.ui.detail.DetailFragment
import com.space.book.ui.home.adapter.SearchAdapter
import com.space.book.ui.home.adapter.ShimmerAdapter
import com.space.book.ui.home.adapter.SliderAdapter
import com.space.book.ui.search.SearchFragment
import com.space.core_ui.util.ParamsItemHandler
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.extension.transformFragment
import com.space.shared.data.BookItem
import com.space.shared.data.book.Category
import com.space.shared.encodeToString
import com.space.core_ui.R
import com.space.shared.data.book.BookHome
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookHomeFragment : ContainerFragment<BookHome>() {

    companion object {
        fun newInstance() = BookHomeFragment()
    }

    override val viewModel: BookHomeViewModel by viewModels()
    override val viewTitle: String = "도서검색"
    private var adapter:RecyclerView.Adapter<*> = ShimmerAdapter()


    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val data = viewModel.view.value?.data ?: return
        val handler = ParamsItemHandler<Category> { category ->
            parentFragmentManager.transformFragment<DetailFragment>(
                R.id.container,
                "detail" to category.encodeToString()
            )
        }
        adapter = ConcatAdapter(
            SearchAdapter { text ->
                parentFragmentManager.transformFragment<SearchFragment>(
                    R.id.container,
                    "search" to text.encodeToString()
                )
            },
            SliderAdapter(ArrayList(data.image)) {
                viewModel.navigatorImage.openView(requireContext(), it)
            },
            BookAdapter(BookItem("신작도서", data.newBook), handler),
            BookAdapter(BookItem("인기도서", data.bestBook), handler),
            BookAdapter(BookItem("대여정보", data.rentalBook.asReversed()), handler)
        )
        binding.recyclerView.adapter = adapter
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
    }

}