package com.space.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.widget.Toast
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
import com.space.shared.NetworkStatus
import com.space.shared.UiStatusType
import com.space.shared.data.notice_space.SpaceNoticeData
import com.space.shared.type.NoticeSpaceType
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    private val viewModel: HomeViewModel by viewModels()
    private var adapter = ConcatAdapter()

    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = ShimmerAdapter()
    }

    override fun beforeObserverListener() {
        viewModel.homeInfo.observe(this) { result ->
            if (result.uiUiStatusType == UiStatusType.SUCCESS) {
                val data = result.data!!
                adapter = ConcatAdapter(
                    NoticeAdapter(data.notice),
                    SliderAdapter(data.slider) {
                        viewModel.navigatorNoticeSpace.openView(
                            requireContext(),
                            SpaceNoticeData(
                                it.seq,
                                SpaceNoticeData.toSpaceType(it.department)
                            )
                        )
                    },
                    ShortcutAdapter { viewType ->
                        when (viewType) {
                            BOOK -> viewModel.navigatorBook.openView(requireContext())
                            MILEAGE -> viewModel.navigatorMileage.openView(requireContext())
                            CHAPEL -> viewModel.navigatorChapel.openView(requireContext())
                            PARTNERS -> viewModel.navigatorPartners.openView(requireContext())
                            BIBLE -> viewModel.navigatorBible.openView(requireContext())
                            ROTHEM -> viewModel.navigatorRothem.openView(requireContext())
                            TIMETABLE -> viewModel.navigatorTimetable.openView(requireContext())
                            NOTICE -> viewModel.navigatorNotice.openView(requireContext())
                            else -> {}
                        }
                    },
                    KokkosAdapter(data.kokkos) { kokkos ->
                        requireContext().startOpenPdf(kokkos)
                    }
                )
            }
        }
    }

    override fun afterObserverListener() {
        viewModel.homeInfo.observe(viewLifecycleOwner) { result ->
            when (result.uiUiStatusType) {
                UiStatusType.SUCCESS -> {
                    binding.recyclerView.adapter = adapter
                }

                UiStatusType.NO_CONNECTION -> {
                    Toast.makeText(context, "인터넷 연결상태가 좋지 않아 연결할 수 없습니다.", Toast.LENGTH_LONG).show()
                }

                else -> {
                    Toast.makeText(context, "알 수 없는 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }


    }
}