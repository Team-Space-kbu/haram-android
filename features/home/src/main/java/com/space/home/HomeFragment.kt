package com.space.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.home.adapter.ShortcutAdapter
import com.space.home.adapter.KokkosAdapter
import com.space.home.adapter.NoticeAdapter
import com.space.home.adapter.SliderAdapter
import com.space.home.util.startOpenPdf
import com.space.navigator.UiNavigator.*
import dagger.hilt.android.AndroidEntryPoint
import com.space.core_ui.R
import com.space.core_ui.base.ContainerCustomFragment
import com.space.core_ui.showToast
import com.space.home.adapter.ChapelAdapter
import com.space.home.adapter.ShimmerAdapter
import com.space.navigator.UiNavigator
import com.space.shared.UiStatusType
import com.space.shared.model.home.HomeModel
import com.space.shared.data.notice_space.SpaceNoticeData
import com.space.shared.type.NoticeSpaceType


@AndroidEntryPoint
class HomeFragment : ContainerCustomFragment<FragmentEmtpyContainerBinding, HomeModel>(
    R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val viewModel: HomeViewModel by viewModels()

    override fun initView() {
        super.initView()
        if (viewModel.view.value?.uiUiStatusType == UiStatusType.LOADING) {
            binding.recyclerView.adapter = ShimmerAdapter()
        } else {
            binding.recyclerView.adapter = adapter
        }
    }

    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val result = viewModel.view.value?.data ?: return
        adapter = ConcatAdapter(
            NoticeAdapter(result.notice) {
                viewModel.navigatorNoticeSpace.openView(
                    requireContext(),
                    SpaceNoticeData(
                        "1",
                        NoticeSpaceType.SPACE
                    ),
                    it
                )
            },
            SliderAdapter(result.slider) {
                viewModel.navigatorNoticeSpace.openView(
                    requireContext(),
                    SpaceNoticeData(
                        it.seq,
                        SpaceNoticeData.toSpaceType(it.department)
                    )
                )
            },
            ChapelAdapter(result.chapel.first, result.chapel.second),
            ShortcutAdapter(::viewType),
            KokkosAdapter(result.kokkos) { kokkos ->
                requireContext().startOpenPdf(kokkos)
            }
        )
        binding.recyclerView.adapter = adapter

    }


    private fun viewType(viewType: UiNavigator) {
        when (viewType) {
            BOOK -> viewModel.navigatorBook.openView(requireContext())
            MILEAGE -> viewModel.navigatorMileage.openView(requireContext())
            CHAPEL -> viewModel.navigatorChapel.openView(requireContext())
            PARTNERS -> viewModel.navigatorPartners.openView(requireContext())
            BIBLE -> {
                requireContext().showToast("교목실과 협의가 되지 않아 사용할 수 없습니다.")
//                viewModel.navigatorBible.openView(requireContext())
            }

            ROTHEM -> viewModel.navigatorRothem.openView(requireContext())
            TIMETABLE -> viewModel.navigatorTimetable.openView(requireContext())
            NOTICE -> viewModel.navigatorNotice.openView(requireContext())
            else -> {}
        }
    }
}