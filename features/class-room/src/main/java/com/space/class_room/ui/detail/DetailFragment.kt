package com.space.class_room.ui.detail

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.class_room.ui.info.InfoFragment
import com.space.core_ui.R
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.binding.adapter.item.CategoryBoxAdapter
import com.space.core_ui.binding.adapter.view.ItemHeaderAdapter
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.core_ui.extension.transformFragment
import com.space.shared.type.DividerType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: ContainerFragment<List<String>>() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val text by extraNotNull<String>("detail").map { it }

    override val viewModel: DetailViewModel by viewModels()
    override val viewTitle: String = "빈강의실 찾기"
    private var adapter: RecyclerView.Adapter<*> = ConcatAdapter()


    override fun init() {
        super.init()
        viewModel.getDetail(text)
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

    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val data: List<String> = viewModel.view.value?.data ?: return
        adapter = ItemHeaderAdapter(
            title = "빈강의실 조회",
            titleSize = 18f,
            adapter = CategoryBoxAdapter(data) {
                parentFragmentManager.transformFragment<InfoFragment>(
                    R.id.container,
                    "info" to it
                )
            },
            dividerType = DividerType.DefaultMargin10,
            padding = false
        )
        binding.recyclerView.adapter = adapter
    }

}