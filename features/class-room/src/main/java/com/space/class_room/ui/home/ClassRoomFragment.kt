package com.space.class_room.ui.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.class_room.ui.detail.DetailFragment
import com.space.core_ui.R
import com.space.class_room.ui.home.adapter.ClassRoomAdapter
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.FlexGrayLineDecoration
import com.space.core_ui.binding.adapter.item.CategoryBoxAdapter
import com.space.core_ui.binding.adapter.view.ItemHeaderAdapter
import com.space.core_ui.extension.transformFragment
import com.space.shared.type.DividerType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassRoomFragment : ContainerFragment<List<String>>() {

    companion object {
        fun newInstance() = ClassRoomFragment()
    }

    override val viewModel: ClassRoomViewModel by viewModels()
    override val viewTitle: String = "빈강의실 찾기"

    private var adapter: RecyclerView.Adapter<*> = ConcatAdapter()

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            FlexGrayLineDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.screen_margin)
            )
        )
    }

    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val data: List<String> = viewModel.view.value?.data ?: return
        adapter = ConcatAdapter(
            ClassRoomAdapter(),
            ItemHeaderAdapter(
                title = "빈강의실 조회",
                titleSize = 18f,
                adapter = CategoryBoxAdapter(data){
                    parentFragmentManager.transformFragment<DetailFragment>(
                        R.id.container,
                        "detail" to it
                    )
                },
                dividerType = DividerType.DefaultMargin10,
                padding = false
            )
        )
        binding.recyclerView.adapter = adapter
    }
}