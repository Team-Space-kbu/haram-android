package com.space.book.ui.detail


import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.book.ui.common.BookItemAdapter
import com.space.core_ui.R
import com.space.book.ui.detail.adapter.AuthorAdapter
import com.space.book.ui.detail.adapter.DetailInfoAdapter
import com.space.book.ui.detail.adapter.RentalAdapter
import com.space.book.ui.detail.adapter.ShimmerDetailAdapter
import com.space.book.ui.detail.adapter.SignAdapter
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.FlexGrayLineDecoration
import com.space.core_ui.binding.adapter.view.ItemHeaderAdapter
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.core_ui.util.showToast
import com.space.core_ui.extension.transformFragment
import com.space.shared.data.book.BookDetailInfo
import com.space.shared.data.book.BookEtc
import com.space.shared.data.book.Category
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import com.space.shared.type.DividerType
import com.space.shared.type.LayoutType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : ContainerFragment<Pair<BookDetailInfo, BookEtc>>() {

    private val detail by extraNotNull<String>("detail")
        .map { it.decodeFromString<Category>() }

    override val viewModel: DetailViewModel by viewModels()
    override val viewTitle: String = "도서상세"

    private var adapter: RecyclerView.Adapter<*> = ShimmerDetailAdapter()


    override fun init() {
        viewModel.getDetail(detail)
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.descendantFocusability = (ViewGroup.FOCUS_BLOCK_DESCENDANTS)
        binding.recyclerView.addItemDecoration(
            FlexGrayLineDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.screen_margin)
            )
        )
    }

    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val data = viewModel.view.value?.data ?: return
        val related = if (data.second.relateBooks.relatedBooks.isNotEmpty()) {
            ItemHeaderAdapter(
                "추천도서",
                18f,
                BookItemAdapter(data.second.relateBooks.relatedBooks) { category ->
                    parentFragmentManager.transformFragment<DetailFragment>(
                        R.id.container,
                        "detail" to category.encodeToString()
                    )
                },
                LayoutType.HORIZONTAL,
                DividerType.NONE
            )
        } else {
            ConcatAdapter()
        }
        adapter = ConcatAdapter(
            SignAdapter(data.first),
            DetailInfoAdapter(data.first),
            AuthorAdapter(data.first),
            RentalAdapter(data.second.keepBooks.keepBooks),
            related
        )
        binding.recyclerView.adapter = adapter
    }

    override fun beforeEmptyListener() {
        super.beforeEmptyListener()
        parentFragmentManager.popBackStack()
        requireContext().showToast("해당 책은 지원하지 않은 책입니다.")
    }


}