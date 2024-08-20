package com.space.book.ui.detail


import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.R
import com.space.book.ui.common.BookAdapter
import com.space.book.ui.detail.adapter.AuthorAdapter
import com.space.book.ui.detail.adapter.DetailInfoAdapter
import com.space.book.ui.detail.adapter.RentalAdapter
import com.space.book.ui.detail.adapter.ShimmerDetailAdapter
import com.space.book.ui.detail.adapter.SignAdapter
import com.space.core_ui.binding.adapter.DividerItemDecoration
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.core_ui.util.showToast
import com.space.core_ui.extension.transformFragment
import com.space.shared.data.BookItem
import com.space.shared.data.book.BookDetailInfo
import com.space.shared.data.book.Category
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : ContainerFragment<BookDetailInfo>() {

    private val detail by extraNotNull<String>("detail")
        .map { it.decodeFromString<Category>() }

    override val viewModel: DetailViewModel by viewModels()
    override val viewTitle: String = "도서상세정보"

    private val rentalAdapter = RentalAdapter()
    private var adapter: RecyclerView.Adapter<*> = ShimmerDetailAdapter()
    private val bookItemAdapter = BookAdapter(BookItem("추천도서")) { category ->
        parentFragmentManager.transformFragment<DetailFragment>(
            R.id.container,
            "detail" to category.encodeToString()
        )
    }

    override fun init() {
        viewModel.getDetail(detail)
        viewModel.getRental(detail)
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.descendantFocusability = (ViewGroup.FOCUS_BLOCK_DESCENDANTS)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                R.drawable.vw_line_divider,
                5,
                5
            )
        )
    }

    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val data = viewModel.view.value?.data ?: return
        adapter = ConcatAdapter(
            SignAdapter(data),
            DetailInfoAdapter(data),
            AuthorAdapter(data),
            rentalAdapter,
            bookItemAdapter
        )
        binding.recyclerView.adapter = adapter
    }

    override fun beforeEmptyListener() {
        super.beforeEmptyListener()
        parentFragmentManager.popBackStack()
        requireContext().showToast("해당 책은 지원하지 않은 책입니다.")
    }

    override fun beforeObserverListener() {
        super.beforeObserverListener()
        viewModel.rental.observe(this) {
            rentalAdapter.setItem(it.keepBooks.keepBooks)
            bookItemAdapter.setItem(BookItem("추천도서", it.relateBooks.relatedBooks))
        }
    }

}