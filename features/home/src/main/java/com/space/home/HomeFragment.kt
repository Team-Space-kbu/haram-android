package com.space.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.shared.data.home.Kokkos
import com.space.shared.data.home.Slider
import com.space.home.adapter.ShortcutAdapter
import com.space.home.adapter.KokkosAdapter
import com.space.home.adapter.KokkosItemAdapter
import com.space.home.adapter.NoticeAdapter
import com.space.home.adapter.SliderAdapter
import com.space.home.adapter.SliderItemAdapter
import com.space.home.util.ViewType
import com.space.home.util.startOpenPdf
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentEmtpyContainerBinding>(com.space.core_ui.R.layout.fragment_emtpy_container) {

    private val viewModel: HomeViewModel by viewModels()



    private val click = object : ShortcutAdapter.ItemHandler {
        override fun clickShortcut(viewType: ViewType) {
            when (viewType) {
                ViewType.BOOK_HOME ->
                    viewModel.navigatorBook.openBookInfo(requireContext())

                ViewType.MILEAGE ->
                    viewModel.navigatorMileage.openMileage(requireContext())

                ViewType.CHAPEL ->
                    viewModel.navigatorChapel.openChapelInfo(requireContext())

                ViewType.PARTNERS ->
                    viewModel.navigatorPartners.openPartners(requireContext())

                ViewType.BIBLE ->
                    viewModel.navigatorBible.openBible(requireContext())

                else -> {}
            }
        }
    }

    private val sliderClick = object : SliderItemAdapter.ItemHandler {
        override fun clickSlider(slider: Slider) {

        }
    }

    private val kokkosClick = object : KokkosItemAdapter.ItemHandler {
        override fun clickKokkos(kokkos: Kokkos) {
            requireContext().startOpenPdf(kokkos)
        }
    }

    override fun initView() {
        super.initView()
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initListener() {
        super.initListener()
        viewModel.homeInfo.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                NoticeAdapter(it.notice),
                SliderAdapter(it.slider, sliderClick),
                ShortcutAdapter(click),
                KokkosAdapter(it.kokkos, kokkosClick)
            )
            binding.recyclerView.adapter = adapter
        }
    }
}