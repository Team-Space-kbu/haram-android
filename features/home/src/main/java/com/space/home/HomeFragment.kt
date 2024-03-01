package com.space.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.home.adapter.ShortcutAdapter
import com.space.home.adapter.KokkosAdapter
import com.space.home.adapter.NoticeAdapter
import com.space.home.adapter.SliderAdapter
import com.space.home.util.startOpenPdf
import com.space.navigator.UiNavigator.*
import dagger.hilt.android.AndroidEntryPoint
import com.space.core_ui.R
import com.space.home.adapter.ShimmerAdapter


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    private val viewModel: HomeViewModel by viewModels()

    override fun initView() {
        super.initView()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = ShimmerAdapter()
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        viewModel.homeInfo.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                NoticeAdapter(it.notice),
                SliderAdapter(it.slider) {

                },
                ShortcutAdapter { viewType ->
                    when (viewType) {
                        BOOK -> navigatorBook.openView(requireContext())
                        MILEAGE -> navigatorMileage.openView(requireContext())
                        CHAPEL -> navigatorChapel.openView(requireContext())
                        PARTNERS -> navigatorPartners.openView(requireContext())
                        BIBLE -> navigatorBible.openView(requireContext())
                        ROTHEM -> navigatorRothem.openView(requireContext())
                        TIMETABLE -> navigatorTimetable.openView(requireContext())
                        NOTICE -> navigatorNotice.openView(requireContext())
                        else -> {}
                    }
                },
                KokkosAdapter(it.kokkos) { kokkos ->
                    requireContext().startOpenPdf(kokkos)
                }
            )
            binding.recyclerView.adapter = adapter
        }
    }
}