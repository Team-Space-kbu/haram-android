package com.space.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.home.adapter.ShortcutAdapter
import com.space.home.adapter.KokkosAdapter
import com.space.home.adapter.NoticeAdapter
import com.space.home.adapter.SliderAdapter
import com.space.home.util.ViewType.*
import com.space.home.util.startOpenPdf
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentEmtpyContainerBinding>(com.space.core_ui.R.layout.fragment_emtpy_container) {

    private val viewModel: HomeViewModel by viewModels()

    override fun initView() {
        super.initView()
        binding.lifecycleOwner = viewLifecycleOwner
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
                        BOOK_HOME -> navigatorBook.openBookInfo(requireContext())
                        MILEAGE -> navigatorMileage.openMileage(requireContext())
                        CHAPEL -> navigatorChapel.openChapelInfo(requireContext())
                        PARTNERS -> navigatorPartners.openPartners(requireContext())
                        BIBLE -> navigatorBible.openBible(requireContext())
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