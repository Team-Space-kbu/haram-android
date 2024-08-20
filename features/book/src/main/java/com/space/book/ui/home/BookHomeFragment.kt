package com.space.book.ui.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.book.ui.common.BookItemAdapter
import com.space.book.ui.detail.DetailFragment
import com.space.book.ui.home.adapter.SearchAdapter
import com.space.book.ui.home.adapter.ShimmerAdapter
import com.space.book.ui.home.adapter.SliderAdapter
import com.space.book.ui.search.SearchFragment
import com.space.core_ui.util.ParamsItemHandler
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.extension.transformFragment
import com.space.shared.data.book.Category
import com.space.shared.encodeToString
import com.space.core_ui.R
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.binding.adapter.view.UiHeaderAdapter
import com.space.shared.data.book.BookHome
import com.space.shared.type.DividerType
import com.space.shared.type.LayoutType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookHomeFragment : ContainerFragment<BookHome>() {

    companion object {
        fun newInstance() = BookHomeFragment()
    }

    override val viewModel: BookHomeViewModel by viewModels()
    override val viewTitle: String = "도서검색"
    private var adapter: RecyclerView.Adapter<*> = ShimmerAdapter()


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
            UiHeaderAdapter(
                "신작도서",
                18f,
                BookItemAdapter(data.newBook, handler),
                LayoutType.HORIZONTAL,
                DividerType.NONE
            ),
            UiHeaderAdapter(
                "인기도서",
                18f,
                BookItemAdapter(data.bestBook, handler),
                LayoutType.HORIZONTAL,
                DividerType.NONE
            ),
            UiHeaderAdapter(
                "대여정보",
                18f,
                BookItemAdapter(data.rentalBook, handler),
                LayoutType.HORIZONTAL,
                DividerType.NONE
            )
        )
        binding.recyclerView.adapter = adapter
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            PaddingItemDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.margin_20dp)
            )
        )
    }

}