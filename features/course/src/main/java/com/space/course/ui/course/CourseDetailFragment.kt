package com.space.course.ui.course

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.binding.adapter.view.ItemHeaderAdapter
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.course.ui.course.adapter.CategoryAdapter
import com.space.course.ui.course.adapter.ShimmerCourseAdapter
import com.space.course.util.startOpenPdf
import com.space.shared.data.course.Course
import com.space.shared.type.DividerType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseDetailFragment : ContainerFragment<List<Course>>() {

    companion object {
        fun newInstance() = CourseDetailFragment()
    }

    override val viewModel: CourseDetailViewModel by viewModels()
    override val viewTitle: String = "수업계획서"
    private var adapter: RecyclerView.Adapter<*> = ShimmerCourseAdapter()
    private val text by extraNotNull<String>("detail").map { it }

    private lateinit var courseDetails: CategoryAdapter

    override fun init() {
        super.init()
        viewModel.init(text)
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            PaddingItemDecoration(
                requireContext(),
                resources.getDimensionPixelSize(com.space.core_ui.R.dimen.margin_20dp)
            )
        )
    }

    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val data: List<Course> = viewModel.view.value?.data ?: return
        val nextIndex = 10.coerceAtMost(data.size)
        courseDetails = CategoryAdapter((data.subList(0, nextIndex)).toMutableList()){ it->
            if (it.lectureFile?.isNotBlank() == true){
                viewModel.navigatorPdf.openView(requireContext(), it.lectureFile!!)
            }
        }
        adapter = ItemHeaderAdapter(
            title = "강의선택",
            titleSize = 18f,
            adapter = courseDetails,
            dividerType = DividerType.DefaultMargin10,
            padding = false
        )
        binding.recyclerView.adapter = adapter
    }

    override fun initListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, state: Int) {
                if (!binding.recyclerView.canScrollVertically(1) && state == RecyclerView.SCROLL_STATE_IDLE) {
                    val data = viewModel.view.value?.data ?: return@onScrollStateChanged
                    val index = courseDetails.itemCount
                    val total = data.size
                    if (index < total) {
                        val nextIndex = (index + 10).coerceAtMost(total)
                        val subList = data.subList(index, nextIndex)
                        courseDetails.addItem(subList)
                    }
                }
            }
        })
    }

}