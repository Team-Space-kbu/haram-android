package com.space.book.ui.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.book.ui.common.BookAdapter
import com.space.book.ui.detail.DetailFragment
import com.space.book.ui.home.adapter.SearchAdapter
import com.space.book.ui.home.adapter.ShimmerAdapter
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
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
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
    private val bestAdapter = BookAdapter(BookItem(), handler)
    private val newAdapter = BookAdapter(BookItem(), handler)
    private val rentalAdapter = BookAdapter(BookItem(), handler)
    private val sliderAdapter = SliderAdapter(arrayListOf()) {
        viewModel.navigatorImage.openView(requireContext(), it)
    }
    private val adapter = ConcatAdapter(
        SearchAdapter { text ->
            parentFragmentManager.transformFragment<SearchFragment>(
                R.id.container,
                "search" to text.encodeToString()
            )
        },
        sliderAdapter,
        newAdapter,
        bestAdapter,
        rentalAdapter
    )

    override fun beforeObserverListener() {
        viewModel.view.observe(this) {
            when (it.uiUiStatusType) {
                UiStatusType.SUCCESS -> {
                    newAdapter.setItem(BookItem("신작도서", it.data!!.newBook))
                    bestAdapter.setItem(BookItem("인기도서", it.data!!.bestBook))
                    rentalAdapter.setItem(BookItem("대여정보", it.data!!.rentalBook.asReversed()))
                    sliderAdapter.setList(it.data!!.image)
                }

                UiStatusType.LOGOUT -> {
                    activity?.finishAffinity()
                    viewModel.navigatorLogin.openView(requireContext())
                }

                else -> {

                }
            }

        }
    }

    override fun initView() {
        binding.setVariable(BR.title, "도서검색")
        binding.lifecycleOwner = viewLifecycleOwner
        if (viewModel.view.isInitialized) {
            binding.recyclerView.adapter = adapter
        } else {
            binding.recyclerView.adapter = ShimmerAdapter()
        }
    }

    override fun afterObserverListener() {
        viewModel.view.observe(this) {
           if (it.uiUiStatusType == UiStatusType.SUCCESS){
               binding.recyclerView.adapter = adapter
           }
        }
    }
}