package com.space.course.ui.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.R

import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.binding.adapter.item.CategoryBoxAdapter
import com.space.core_ui.binding.adapter.view.ItemHeaderAdapter
import com.space.core_ui.extension.transformFragment
import com.space.course.ui.course.CourseDetailFragment
import com.space.shared.data.course.CourseHome
import com.space.shared.type.DividerType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseHomeFragment : ContainerFragment<List<CourseHome>>() {

    companion object {
        fun newInstance() = CourseHomeFragment()
    }

    override val viewModel: CourseHomeViewModel by viewModels()
    override val viewTitle: String = "수업계획서"
    private var adapter: RecyclerView.Adapter<*> = ShimmerAdapter()

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
        val data: List<CourseHome> = viewModel.view.value?.data ?: return
        val title: List<String> = data.map { it.title.toString() }
        adapter = ItemHeaderAdapter(
            title = "학과선택",
            titleSize = 18f,
            adapter = CategoryBoxAdapter(title) { courseTitle ->
                parentFragmentManager.transformFragment<CourseDetailFragment>(
                    R.id.container,
                    "detail" to data.find { it.title == courseTitle }?.key
                )
            },
            dividerType = DividerType.DefaultMargin10,
            padding = false
        )
        binding.recyclerView.adapter = adapter
    }

}