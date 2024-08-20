package com.space.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.home.adapter.ShortcutAdapter
import com.space.home.adapter.KokkosAdapter
import com.space.home.adapter.SliderAdapter
import com.space.home.util.startOpenPdf
import com.space.navigator.UiNavigator.*
import dagger.hilt.android.AndroidEntryPoint
import com.space.core_ui.R
import com.space.core_ui.base.ContainerCustomFragment
import com.space.core_ui.util.showToast
import com.space.home.adapter.ChapelAdapter
import com.space.home.adapter.ShimmerAdapter
import com.space.navigator.UiNavigator
import com.space.shared.model.home.HomeModel


@AndroidEntryPoint
class HomeFragment : ContainerCustomFragment<FragmentEmtpyContainerBinding, HomeModel>(
    R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val viewModel: HomeViewModel by viewModels()
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
        val result = viewModel.view.value?.data ?: return
        adapter = ConcatAdapter(
            SliderAdapter(result.notice) {
                viewModel.navigatorNoticeSpace.openView(
                    requireContext(),
                    it.noticeSeq?: ""
                )
            },
            ChapelAdapter(
                result.chapel?.first ?: false,
                result.chapel?.second
            ),
            ShortcutAdapter(::viewType),
            KokkosAdapter(result.kokkos) {
                requireContext().startOpenPdf(it)
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