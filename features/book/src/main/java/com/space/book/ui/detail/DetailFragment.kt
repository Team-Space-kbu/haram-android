package com.space.book.ui.detail


import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.R
import com.space.book.ui.common.BookAdapter
import com.space.book.ui.detail.adapter.AuthorAdapter
import com.space.book.ui.detail.adapter.DetailInfoAdapter
import com.space.book.ui.detail.adapter.RentalAdapter
import com.space.book.ui.detail.adapter.SignAdapter
import com.space.core_ui.BR
import com.space.core_ui.DividerItemDecoration
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.transformFragment
import com.space.shared.data.BookItem
import com.space.shared.data.book.Category
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    private val detail by extraNotNull<String>("detail")
        .map { encodeString -> encodeString.decodeFromString<Category>() }

    private val viewModel: DetailViewModel by viewModels()
    private val rentalAdapter = RentalAdapter()
    private val bookBookItemAdapter = BookAdapter(BookItem("추천도서")) { category ->
        parentFragmentManager.transformFragment<DetailFragment>(
            R.id.container,
            "detail" to category.encodeToString()
        )
    }

    override fun init() {
        detail.let {
            viewModel.getDetail(it)
            viewModel.getRental(it)
        }
    }

    override fun initView() {
        binding.setVariable(BR.title,   "도서상세정보")
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.descendantFocusability = (ViewGroup.FOCUS_BLOCK_DESCENDANTS)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                R.drawable.line_divider,
                5,
                5
            )
        )
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.detail.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                SignAdapter(it),
                DetailInfoAdapter(it),
                AuthorAdapter(it),
                rentalAdapter,
                bookBookItemAdapter
            )
            binding.recyclerView.adapter = adapter
        }
        viewModel.rental.observe(viewLifecycleOwner) {
            rentalAdapter.setItem(it.keepBooks.keepBooks)
            bookBookItemAdapter.setItem(BookItem("추천도서", it.relateBooks.relatedBooks))
        }
    }
}