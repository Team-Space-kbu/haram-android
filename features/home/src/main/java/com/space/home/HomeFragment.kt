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
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.showToast
import com.space.home.adapter.ChapelAdapter
import com.space.home.adapter.ShimmerAdapter
import com.space.navigator.UiNavigator
import com.space.shared.UiStatusType
import com.space.shared.data.notice_space.SpaceNoticeData
import com.space.shared.type.NoticeSpaceType
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    private val viewModel: HomeViewModel by viewModels()
    private var adapter = ConcatAdapter()
    private val chapelAdapter by lazy {
        ChapelAdapter(viewModel.chapelTime())
    }

    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = ShimmerAdapter()
    }

    override fun beforeObserverListener() {
        viewModel.chapel.observe(this) {
            val data = it ?: return@observe
            Timber.i(data.toString())
            chapelAdapter.setChapel(data)
        }
        viewModel.view.observe(this) { result ->
            when (result.uiUiStatusType) {
                UiStatusType.SUCCESS -> {
                    val data = result.data ?: return@observe
                    adapter = ConcatAdapter(
                        NoticeAdapter(data.notice) {
                            viewModel.navigatorNoticeSpace.openView(
                                requireContext(),
                                SpaceNoticeData(
                                    "1",
                                    NoticeSpaceType.SPACE
                                ),
                                it
                            )
                        },
                        SliderAdapter(data.slider) {
                            viewModel.navigatorNoticeSpace.openView(
                                requireContext(),
                                SpaceNoticeData(
                                    it.seq,
                                    SpaceNoticeData.toSpaceType(it.department)
                                )
                            )
                        },
                        chapelAdapter,
                        ShortcutAdapter(::viewType),
                        KokkosAdapter(data.kokkos) { kokkos ->
                            requireContext().startOpenPdf(kokkos)
                        }
                    )
                }

                UiStatusType.LOGOUT -> {
                    activity?.finishAffinity()
                    viewModel.navigatorLogin.openView(requireContext())
                }

                else -> { }
            }
        }
    }

    override fun afterObserverListener() {
        viewModel.view.observe(viewLifecycleOwner) { result ->
            when (result.uiUiStatusType) {
                UiStatusType.SUCCESS ->
                    binding.recyclerView.adapter = adapter

                UiStatusType.NO_CONNECTION ->
                    requireContext().showToast("인터넷 연결상태가 좋지 않아 연결할 수 없습니다.")

                UiStatusType.LOGOUT ->
                    requireContext().showToast("로그아웃되었습니다.")

                else ->
                    requireContext().showToast("알 수 없는 오류가 발생했습니다")
            }
        }
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
            else -> { }
        }
    }
}