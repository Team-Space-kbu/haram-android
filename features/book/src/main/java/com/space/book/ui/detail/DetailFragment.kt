package com.space.book.ui.detail


import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.book.R
import com.space.book.databinding.FragmentBookContainerBinding
import com.space.book.ui.common.BookAdapter
import com.space.book.ui.common.BookItemAdapter
import com.space.book.ui.detail.adapter.AuthorAdapter
import com.space.book.ui.detail.adapter.DetailInfoAdapter
import com.space.book.ui.detail.adapter.RentalAdapter
import com.space.book.ui.detail.adapter.SignAdapter
import com.space.core_ui.DividerItemDecoration
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.transformFragment
import com.space.shared.data.Item
import com.space.shared.data.book.Category
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentBookContainerBinding>(R.layout.fragment_book_container) {

    private val detail by extraNotNull<String>("detail")
        .map { encodeString ->
            encodeString.decodeFromString<Category>()
        }

    private val click = object : BookItemAdapter.ItemHandler {
        override fun clickCategory(category: Category) {
            parentFragmentManager.transformFragment<DetailFragment>(
                R.id.container,
                "detail" to category.encodeToString()
            )
        }
    }

    private val viewModel: DetailViewModel by viewModels()
    private val rentalAdapter = RentalAdapter()
    private val bookItemAdapter = BookAdapter(Item())

    override fun init() {
        detail.let {
            viewModel.getDetail(it)
            viewModel.getRental(it)
        }
    }

    override fun initView() {
        binding.titleToolbar.text = "도서상세정보"
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initListener() {
        viewModel.detail.observe(this) {
            val adapter = ConcatAdapter(
                SignAdapter(it),
                DetailInfoAdapter(it),
                AuthorAdapter(it),
                rentalAdapter,
                bookItemAdapter
            )
            binding.recyclerView.adapter = adapter
            binding.recyclerView.descendantFocusability = (ViewGroup.FOCUS_BLOCK_DESCENDANTS)
            binding.recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    com.space.core_ui.R.drawable.line_divider,
                    5,
                    5
                )
            )
        }

        viewModel.rental.observe(this) {
            rentalAdapter.setItem(it.keepBooks.keepBooks)
            bookItemAdapter.setItem(
                Item(
                    title = "추천도서",
                    list = it.relateBooks.relatedBooks,
                    event = click
                )
            )
        }
    }
}